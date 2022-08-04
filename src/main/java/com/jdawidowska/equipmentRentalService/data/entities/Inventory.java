package com.jdawidowska.equipmentRentalService.data.entities;

import com.jdawidowska.equipmentRentalService.model.EquipmentEnum;
import lombok.*;
import javax.persistence.*;

@Data
@Getter
@Setter
@ToString
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    EquipmentEnum itemName;
    Integer totalAmount;
    Integer availableAmount;

    public Inventory(Long id, EquipmentEnum itemName, Integer totalAmount, Integer availableAmount) {
        this.id = id;
        this.itemName = itemName;
        this.totalAmount = totalAmount;
        this.availableAmount = availableAmount;
    }

    public Inventory() {
    }
}
