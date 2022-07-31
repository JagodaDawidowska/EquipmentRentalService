package com.jdawidowska.service.data.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class Rented {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long idCustomer;
    Long idItem;
    Integer amount;
//
}