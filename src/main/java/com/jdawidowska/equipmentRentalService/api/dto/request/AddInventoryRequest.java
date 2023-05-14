package com.jdawidowska.equipmentRentalService.api.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class AddInventoryRequest {

    @NotNull
    private final String itemName;
    @NotNull
    private final Integer totalAmount;
}
