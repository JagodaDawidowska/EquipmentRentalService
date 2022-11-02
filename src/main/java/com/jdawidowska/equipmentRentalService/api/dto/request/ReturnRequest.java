package com.jdawidowska.equipmentRentalService.api.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class ReturnRequest {

    @NotNull
    private Long idRentedInventory;
    @NotNull
    private String feedback;
}