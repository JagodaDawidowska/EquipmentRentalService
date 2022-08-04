package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.request.RenteringRequest;
import com.jdawidowska.equipmentRentalService.api.dto.response.MessageEnum;
import com.jdawidowska.equipmentRentalService.api.dto.response.RenteringResponse;
import com.jdawidowska.equipmentRentalService.services.RenteringService;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentingController {

    public final RenteringService renteringService;

    public RentingController(RenteringService renteringService) {
        this.renteringService = renteringService;
    }

    //nie widziałam zadnego ResponseEntity z postem
    //pozniej sprawdze jak sie zwraca kody httpstatusu bo to chyba wymaga restTample
    @PostMapping
    public ResponseEntity<RenteringResponse> rent(@RequestBody RenteringRequest renteringRequest, @RequestParam("IdItem") Long IdItem){


        //ify(biore metode rent z service co zwraca booolean
        if(renteringService.rent(renteringRequest, IdItem)==true){
            RenteringResponse correctResponse = new RenteringResponse(MessageEnum.POPRAWNE_WYPOZYCZENIE);
            renteringService.rent(renteringRequest,IdItem);
            return new ResponseEntity<>(correctResponse, HttpStatus.OK);
        }else{
            RenteringResponse incorrectResponse = new RenteringResponse(MessageEnum.POPRAWNE_WYPOZYCZENIE);
            return new ResponseEntity<>(incorrectResponse, HttpStatus.BAD_REQUEST);
                                                            //chyba bardziej brak zasobu ?
        }



    }
}
