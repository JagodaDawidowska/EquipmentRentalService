package com.jdawidowska.equipmentRentalService.api.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class RegisterUserRequest {

    @NotNull
    private final String name;
    @NotNull
    private final String surname;
    @NotNull
    private final String email;
    @NotNull
    private final String password;
}