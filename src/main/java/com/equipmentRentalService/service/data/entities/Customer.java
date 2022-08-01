package com.equipmentRentalService.service.data.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    String email;
    String password;

}