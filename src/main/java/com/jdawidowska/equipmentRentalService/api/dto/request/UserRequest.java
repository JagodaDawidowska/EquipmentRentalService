package com.jdawidowska.equipmentRentalService.api.dto.request;

import com.jdawidowska.equipmentRentalService.model.Role;

public class UserRequest {

    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final Role role;

    public UserRequest(String name, String surname, String email, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}