package com.jdawidowska.equipmentRentalService.data.entities;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private Integer totalAmount;
    private Integer availableAmount;
}