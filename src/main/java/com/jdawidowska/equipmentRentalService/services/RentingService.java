package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.RentRequest;
import com.jdawidowska.equipmentRentalService.api.dto.request.ReturnRequest;
import com.jdawidowska.equipmentRentalService.data.entities.Feedback;
import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import com.jdawidowska.equipmentRentalService.data.entities.UserRentHistory;
import com.jdawidowska.equipmentRentalService.data.repos.FeedbackRepository;
import com.jdawidowska.equipmentRentalService.data.repos.InventoryRepository;
import com.jdawidowska.equipmentRentalService.data.repos.RentedInventoryRepository;
import com.jdawidowska.equipmentRentalService.data.repos.UserRentHistoryRepository;
import com.jdawidowska.equipmentRentalService.exception.ItemNotFoundException;
import com.jdawidowska.equipmentRentalService.exception.RentHistoryNotFoundException;
import com.jdawidowska.equipmentRentalService.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void rentItem(RentRequest rentRequest) throws ItemNotFoundException {

        //
        // znajdujemy konkretny equipment przez ID
        //
        Inventory inventory = inventoryRepository.findById(rentRequest.getIdItem()).orElse(null);
        if (inventory == null || inventory.getAvailableAmount() <= 0) {
            throw new ItemNotFoundException();
        }
        //
        // wypozyczamy go (zmniejszamy ilosc dostepnych)
        //
        inventoryRepository.rentItem(rentRequest.getIdItem());

        //
        // dodajemy wpis do historii o wypozyczeniu -> id historii potrzebujemy do wpisania w tabeli wypozyczen
        //
        UserRentHistory history = new UserRentHistory();
        history.setIdUser(rentRequest.getIdUser());
        history.setIdItem(rentRequest.getIdItem());
        history.setRentDate(DateUtil.getCurrentDate());

        UserRentHistory historyWithGeneratedID = userRentHistoryRepository.save(history);

        //
        // dodajemy wpis do tabeli wypozyczen
        // jesli dany uzytkownik juz wypozyczyl item z takim idItem to zapdejtuj liczbe wypożyczonych itemów
        //
        if (rentedInventoryRepository.existsByIdUserAndIdItem(rentRequest.getIdUser(), rentRequest.getIdItem())) {
            Long requiredId = rentedInventoryRepository.getIdRentedInventoryByIdUserAndIdItem(rentRequest.getIdUser(), rentRequest.getIdItem());
            rentedInventoryRepository.incrementAmount(requiredId);
        } else {
            RentedInventory rentedInventory = new RentedInventory();
            rentedInventory.setIdUser(rentRequest.getIdUser());
            rentedInventory.setIdItem(rentRequest.getIdItem());
            rentedInventory.setIdHistory(historyWithGeneratedID.getId());
            rentedInventory.setAmount(1); //TODO
            rentedInventoryRepository.save(rentedInventory);
        }
    }

    @Transactional
    public void returnItem(ReturnRequest returnRequest) throws ItemNotFoundException, RentHistoryNotFoundException {
        //1
        //Sprawdzamy czy dany record istnieje w RentedInventory, jesli istnieje zwracamy przedimot, jelsi nie zwracamy false
        RentedInventory rentedInventory = rentedInventoryRepository.findById(returnRequest.getIdRentedInventory()).orElse(null);
        if (rentedInventory == null) {
            throw new ItemNotFoundException();
        }
        inventoryRepository.returnItem(rentedInventory.getIdItem());

        //2
        //dodajemy do history date oddania itemu
        UserRentHistory userRentHistory = userRentHistoryRepository.findById(rentedInventory.getIdHistory()).orElse(null);
        if (userRentHistory == null) {
            throw new RentHistoryNotFoundException();
        }
        userRentHistoryRepository.updateReturnDate(rentedInventory.getIdHistory(), DateUtil.getCurrentDate());

        //3
        //usuwamy ilosc wypozyczonych rzeczy w RentedInventory
        Integer rentedInventoryAmount = rentedInventory.getAmount();
        //jesli ilosc rzeczy jest wieksza od zera to zmiejszamy ilosc o 1
        if (rentedInventoryAmount == 1) {
            //jesli ilosc jest mniejsza od zera usuwamy caly record
            rentedInventoryRepository.deleteById(rentedInventory.getId());
        } else {
            rentedInventoryRepository.decreaseAmount(rentedInventory.getId());
        }

        //4
        //zapisywanie feedbacku
        if (returnRequest.getFeedback() != null && !returnRequest.getFeedback().isEmpty()) {
            Feedback feedback = new Feedback();
            feedback.setIdUser(rentedInventory.getIdUser());
            feedback.setContent(returnRequest.getFeedback());
            feedbackRepository.save(feedback);
        }
    }
}
