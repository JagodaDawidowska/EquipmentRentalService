package com.jdawidowska.service.api.dto.request;

public class CustomerRequest {

    private final String name;
    private final String surname;
    private final String email;
    private final String password;

    public CustomerRequest(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
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
}
