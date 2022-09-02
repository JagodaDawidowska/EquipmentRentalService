package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.RentingRequest;
import com.jdawidowska.equipmentRentalService.api.dto.request.ReturnRequest;
import com.jdawidowska.equipmentRentalService.data.entities.Feedback;
import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import com.jdawidowska.equipmentRentalService.data.repos.FeedbackRepository;
import com.jdawidowska.equipmentRentalService.data.repos.InventoryRepository;
import com.jdawidowska.equipmentRentalService.data.repos.RentedInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentingService {

    private final InventoryRepository inventoryRepository;

    private final RentedInventoryRepository rentedInventoryRepository;
    private final FeedbackRepository feedbackRepository;


    public RentingService(InventoryRepository inventoryRepository, RentedInventoryRepository rentedInventoryRepository, FeedbackRepository feedbackRepository) {
        this.inventoryRepository = inventoryRepository;
        this.rentedInventoryRepository = rentedInventoryRepository;
        this.feedbackRepository = feedbackRepository;
    }

    public boolean rent(RentingRequest rentingRequest) {

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
        }
    }

    public boolean returnItem(ReturnRequest returnRequest) {
        Inventory inventory = inventoryRepository.findById(returnRequest.getIdItem()).orElse(null);
        if (inventory == null) {
            return false;
        }
        if (inventory.getAvailableAmount() < inventory.getTotalAmount()) {
            inventoryRepository.returnItem(returnRequest.getIdItem());
            rentedInventoryRepository.returnInventoryReturn(returnRequest.getIdUser(), returnRequest.getIdItem());
            RentedInventory rentedInventory = rentedInventoryRepository.findByIdUserAndIdItem(returnRequest.getIdUser(), returnRequest.getIdItem()).orElse(null);

            if (rentedInventory.getAmount() == 0) {
                Long definedId = rentedInventoryRepository.getDefinedId(returnRequest.getIdUser(), returnRequest.getIdItem());
                rentedInventoryRepository.deleteById(definedId);
            }
            if(returnRequest.getFeedback() != null && !returnRequest.getFeedback().isEmpty()){
                Feedback feedback = new Feedback();
                feedback.setIdUser(returnRequest.getIdUser());
                feedback.setContent(returnRequest.getFeedback());
                feedbackRepository.save(feedback);
            }
            return true;
        } else return false;
    }
}
