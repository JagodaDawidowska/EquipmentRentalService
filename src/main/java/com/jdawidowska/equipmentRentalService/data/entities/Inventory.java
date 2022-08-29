package com.jdawidowska.equipmentRentalService.data.entities;

import com.jdawidowska.equipmentRentalService.model.EquipmentEnum;
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
    String itemName;
    Integer totalAmount;
    Integer availableAmount;


}
