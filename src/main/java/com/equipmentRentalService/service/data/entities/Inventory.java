package com.equipmentRentalService.service.data.entities;

import com.equipmentRentalService.service.model.EquipmentEnum;
import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity


public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)

    EquipmentEnum itemName;
    Integer totalAmount;
    Integer avaiableAmount;

}
