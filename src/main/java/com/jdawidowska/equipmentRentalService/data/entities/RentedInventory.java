package com.jdawidowska.equipmentRentalService.data.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class RentedInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idHistory;
    private Long idUser;
    private Long idItem;
    private Integer amount;
}