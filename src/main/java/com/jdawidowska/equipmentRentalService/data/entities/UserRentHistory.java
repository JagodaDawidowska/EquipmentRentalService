package com.jdawidowska.equipmentRentalService.data.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class UserRentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUser;
    private Long idItem;
    private Long rentDate;
    private Long returnDate;
}
