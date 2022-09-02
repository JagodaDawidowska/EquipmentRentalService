package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.request.RentingRequest;
import com.jdawidowska.equipmentRentalService.api.dto.request.ReturnRequest;
import com.jdawidowska.equipmentRentalService.api.dto.response.RentingEnum;
import com.jdawidowska.equipmentRentalService.api.dto.response.RentingResponse;
import com.jdawidowska.equipmentRentalService.services.RentingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/renting")
public class RentingController {

    private final RentingService rentingService;

    public RentingController(RentingService rentingService) {
        this.rentingService = rentingService;
    }

    @PostMapping
    @RequestMapping("/rent")
    public ResponseEntity<RentingResponse> rentEquipment(@RequestBody RentingRequest rentingRequest) {
        if (rentingService.rent(rentingRequest) == true) {
            RentingResponse correctResponse = new RentingResponse(RentingEnum.RENT_SUCCESS);
            return new ResponseEntity<>(correctResponse, HttpStatus.OK);
        } else {
            RentingResponse incorrectResponse = new RentingResponse(RentingEnum.RENT_FAIL);
            return new ResponseEntity<>(incorrectResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @RequestMapping("/return")
    public ResponseEntity<RentingResponse> returnEquipment(@RequestBody ReturnRequest returnRequest){
        if(rentingService.returnItem(returnRequest) == true){
            RentingResponse correctResponse = new RentingResponse(RentingEnum.RETURN_SUCCESS);
            return new ResponseEntity<>(correctResponse, HttpStatus.OK);
        } else {
            RentingResponse incorrectResponse = new RentingResponse(RentingEnum.RETURN_FAIL);
            return new ResponseEntity<>(incorrectResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
