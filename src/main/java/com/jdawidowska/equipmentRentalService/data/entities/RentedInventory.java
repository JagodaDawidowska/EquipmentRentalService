package com.jdawidowska.equipmentRentalService.data.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class RentedInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long idHistory;
    Long idUser;
    Long idItem;
    Integer amount;

    public RentedInventory(Long idUser, Long idItem) {
        this.idUser = idUser;
        this.idItem = idItem;
    }
}