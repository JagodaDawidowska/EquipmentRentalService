package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.RentingRequest;
import com.jdawidowska.equipmentRentalService.api.dto.request.ReturnRequest;
import com.jdawidowska.equipmentRentalService.data.entities.Feedback;
import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import com.jdawidowska.equipmentRentalService.data.entities.UserRentHistory;
import com.jdawidowska.equipmentRentalService.data.repos.FeedbackRepository;
import com.jdawidowska.equipmentRentalService.data.repos.InventoryRepository;
import com.jdawidowska.equipmentRentalService.data.repos.RentedInventoryRepository;
import com.jdawidowska.equipmentRentalService.data.repos.UserRentHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class RentingService {

    private final InventoryRepository inventoryRepository;
    private final RentedInventoryRepository rentedInventoryRepository;
    private final FeedbackRepository feedbackRepository;
    private final UserRentHistoryRepository userRentHistoryRepository;

    public RentingService(InventoryRepository inventoryRepository, RentedInventoryRepository rentedInventoryRepository, FeedbackRepository feedbackRepository, UserRentHistoryRepository userRentHistoryRepository) {
        this.inventoryRepository = inventoryRepository;
        this.rentedInventoryRepository = rentedInventoryRepository;
        this.feedbackRepository = feedbackRepository;
        this.userRentHistoryRepository = userRentHistoryRepository;
    }

    @Transactional
    public boolean rent(RentingRequest rentingRequest) {

        //
        // znajdujemy konkretny equipment przez ID
        //
        Inventory inventory = inventoryRepository.findById(rentingRequest.getIdItem()).orElse(null);
        if (inventory == null || inventory.getAvailableAmount() <= 0) {
            return false;
        }
        //
        // wypozyczamy go (zmniejszamy ilosc dostepnych)
        //
        inventoryRepository.rentItem(rentingRequest.getIdItem());

        //
        // dodajemy wpis do historii o wypozyczeniu -> id historii potrzebujemy do wpisania w tabeli wypozyczen
        //
        UserRentHistory history = new UserRentHistory();
        history.setIdUser(rentingRequest.getIdUser());
        history.setIdItem(rentingRequest.getIdItem());
        history.setRentDate(new Date(System.currentTimeMillis()));

        UserRentHistory historyWithGeneratedID = userRentHistoryRepository.save(history);

        //
        // dodajemy wpis do tabeli wypozyczen
        //
        RentedInventory rentedInventory = new RentedInventory();
        rentedInventory.setIdUser(rentingRequest.getIdUser());
        rentedInventory.setIdItem(rentingRequest.getIdItem());
        rentedInventory.setAmount(1);
        rentedInventory.setIdHistory(historyWithGeneratedID.getId());
        rentedInventoryRepository.save(rentedInventory);

        return true;
        /*
        Inventory inventory = inventoryRepository.findById(rentingRequest.getIdItem()).orElse(null);
        if (inventory == null) {
            return false;
        }
        if (inventory.getAvailableAmount() > 0) {
            List<Long> idList = rentedInventoryRepository.getDefinedIds(rentingRequest.getIdUser(), rentingRequest.getIdItem());
            if (!idList.isEmpty()) {
                rentedInventoryRepository.rentedInventoryRent(rentingRequest.getIdUser(), rentingRequest.getIdItem());
                inventoryRepository.rentItem(rentingRequest.getIdItem());
                return true;
            } else {
                RentedInventory rentedInventory = new RentedInventory();
                rentedInventory.setIdUser(rentingRequest.getIdUser());
                rentedInventory.setIdItem(rentingRequest.getIdItem());
                rentedInventory.setAmount(1);
                rentedInventoryRepository.save(rentedInventory);
                inventoryRepository.rentItem(rentingRequest.getIdItem());
                return true;
            }
        } else {
            return false;
        }*/
    }

    @Transactional
    public boolean returnItem(ReturnRequest returnRequest) {
        RentedInventory rentedInventory = rentedInventoryRepository.findById(returnRequest.getIdRentedInventory()).orElse(null);
        if(rentedInventory == null){
            return false;
        }
        inventoryRepository.returnItem(rentedInventory.getIdItem());
        UserRentHistory userRentHistory = userRentHistoryRepository.findById(rentedInventory.getIdHistory()).orElse(null);
        if(userRentHistory == null){
            return false;
        }
        userRentHistoryRepository.updateReturnDate(rentedInventory.getIdHistory(), new Date(System.currentTimeMillis()));
        rentedInventoryRepository.deleteById(rentedInventory.getId());

        if(returnRequest.getFeedback() != null && !returnRequest.getFeedback().isEmpty()){
            Feedback feedback = new Feedback();
            feedback.setIdUser(rentedInventory.getIdUser());
            feedback.setContent(returnRequest.getFeedback());
            feedbackRepository.save(feedback);
        }

        return true;
        /*
        Inventory inventory = inventoryRepository.findById(returnRequest.getIdItem()).orElse(null);
        if (inventory == null) {
            return false;
        }
        if (inventory.getAvailableAmount() < inventory.getTotalAmount()) {
            //return item to INVENTORY
            inventoryRepository.returnItem(returnRequest.getIdItem());
            //return item in RENTED_INVENTORY
            rentedInventoryRepository.returnInventoryReturn(returnRequest.getIdUser(), returnRequest.getIdItem());
            RentedInventory rentedInventory = rentedInventoryRepository.findByIdUserAndIdItem(returnRequest.getIdUser(), returnRequest.getIdItem()).orElse(null);
            //add return in
            if (rentedInventory.getAmount() == 0) {
                //delete record in RENTED_INVENTORY
                Long definedId = rentedInventoryRepository.getDefinedId(returnRequest.getIdUser(), returnRequest.getIdItem());
                rentedInventoryRepository.deleteById(definedId);
            }
            if(returnRequest.getFeedback() != null && !returnRequest.getFeedback().isEmpty()){
                //add record in FEEDBACK
                Feedback feedback = new Feedback();
                feedback.setIdUser(returnRequest.getIdUser());
                feedback.setContent(returnRequest.getFeedback());
                feedbackRepository.save(feedback);
            }
            Date dateReturn = new Date(System.currentTimeMillis());
            return true;
        } else return false;

         */
    }
}
