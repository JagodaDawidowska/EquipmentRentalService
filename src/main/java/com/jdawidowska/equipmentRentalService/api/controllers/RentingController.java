package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.request.RentingRequest;
import com.jdawidowska.equipmentRentalService.api.dto.response.MessageEnum;
import com.jdawidowska.equipmentRentalService.api.dto.response.RentingResponse;
import com.jdawidowska.equipmentRentalService.services.RentingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/renting")
public class RentingController {

    private final RentingService rentingService;

    public RentingController(RentingService rentingService) {
        this.rentingService = rentingService;
    }

    @PostMapping
    @RequestMapping("/rent")
    public ResponseEntity<RentingResponse> rent(@RequestBody RentingRequest rentingRequest) {

        if (rentingService.rent(rentingRequest) == true) {
            RentingResponse correctResponse = new RentingResponse(MessageEnum.RENT_SUCCESS);
            return new ResponseEntity<>(correctResponse, HttpStatus.OK);
        } else {
            RentingResponse incorrectResponse = new RentingResponse(MessageEnum.RENT_FAIL);
            return new ResponseEntity<>(incorrectResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @RequestMapping("/return")
    public ResponseEntity<RentingResponse> returnEquipment(@RequestBody RentingRequest rentingRequest){
        if(rentingService.returnItem(rentingRequest)){
            RentingResponse correctResponse = new RentingResponse(MessageEnum.RETURN_SUCCESS);
            return new ResponseEntity<>(correctResponse, HttpStatus.OK);
        } else {
            RentingResponse incorrectResponse = new RentingResponse(MessageEnum.RETURN_FAIL);
            return new ResponseEntity<>(incorrectResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
