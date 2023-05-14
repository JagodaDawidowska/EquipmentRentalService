package com.jdawidowska.equipmentRentalService.api.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class RentRequest {

    @NotNull
    private long idUser;
    @NotNull
    private long idItem;
}
