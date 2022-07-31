package com.jdawidowska.service.data.entities;

import com.jdawidowska.service.model.EquipmentEnum;
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
    EquipmentEnum name;
    Integer total_amount;
    Integer avaiable_amount;

}
