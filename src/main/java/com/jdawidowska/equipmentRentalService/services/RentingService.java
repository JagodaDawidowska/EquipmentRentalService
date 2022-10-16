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
        history.setRentDate(DateUtil.getCurrentDate());

        UserRentHistory historyWithGeneratedID = userRentHistoryRepository.save(history);

        //
        // dodajemy wpis do tabeli wypozyczen
        //jesli dany uzytkownik juz wypozyczyl item z takim idItem to zabdejtuj liczbe wypożyczonych itemów
        if (rentedInventoryRepository.existsByIdUserAndIdItem(rentingRequest.getIdUser(), rentingRequest.getIdItem())) {
            Long requiredId = rentedInventoryRepository.getIdRentedInventoryByIdUserAndIdItem(rentingRequest.getIdUser(), rentingRequest.getIdItem());
            rentedInventoryRepository.doIncrementAmount(requiredId);
            return true;
        } else {
            RentedInventory rentedInventory = new RentedInventory();
            rentedInventory.setIdUser(rentingRequest.getIdUser());
            rentedInventory.setIdItem(rentingRequest.getIdItem());
            rentedInventory.setIdHistory(historyWithGeneratedID.getId());
            rentedInventory.setAmount(1);
            rentedInventoryRepository.save(rentedInventory);
        }

        return true;
    }

    @Transactional
    public boolean returnItem(ReturnRequest returnRequest) {
        //1
        //Sprawdzamy czy dany record istnieje w RentedInventory, jesli istnieje zwracamy przedimot, jelsi nie zwracamy false
        RentedInventory rentedInventory = rentedInventoryRepository.findById(returnRequest.getIdRentedInventory()).orElse(null);
        if (rentedInventory == null) {
            return false;
        }
        inventoryRepository.returnItem(rentedInventory.getIdItem());

        //2
        //dodajemy do history date oddania itemu
        UserRentHistory userRentHistory = userRentHistoryRepository.findById(rentedInventory.getIdHistory()).orElse(null);
        if (userRentHistory == null) {
            return false;
        }
        userRentHistoryRepository.updateReturnDate(rentedInventory.getIdHistory(), DateUtil.getCurrentDate());

        //3
        //usuwamy ilosc wypozyczonych rzeczy w RentedInventory
        Integer amountRentedInventory = rentedInventory.getAmount();
        //jesli ilosc rzeczy jest wieksza od zera to zmiejszamy ilosc o 1
        if (amountRentedInventory == 1) {
            //jesli ilosc jest mniejsza od zera usuwamy caly record
            rentedInventoryRepository.deleteById(rentedInventory.getId());
        } else {
            rentedInventoryRepository.doDecreaseAmount(rentedInventory.getId());
        }

        //4
        //zapisywanie feedbacku
        if (returnRequest.getFeedback() != null && !returnRequest.getFeedback().isEmpty()) {
            Feedback feedback = new Feedback();
            feedback.setIdUser(rentedInventory.getIdUser());
            feedback.setContent(returnRequest.getFeedback());
            feedbackRepository.save(feedback);
        }
        return true;
    }
}
