package com.jdawidowska.equipmentRentalService.data.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class rentedInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long idCustomer;
    Long idItem;
    Integer amount;

}