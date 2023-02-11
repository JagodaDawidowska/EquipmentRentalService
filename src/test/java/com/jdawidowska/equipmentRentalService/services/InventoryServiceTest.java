package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.data.repos.InventoryRepository;
import com.jdawidowska.equipmentRentalService.exception.ItemNotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class InventoryServiceTest {

    @InjectMocks
    private InventoryService systemUnderTest;

    @Mock
    private InventoryRepository inventoryRepository;

    @SneakyThrows
    @Test
    void removeItem_should_throw_exception_when_entity_missing() {
        Mockito
                .when(inventoryRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class,
                () -> systemUnderTest.removeItem(999L));
    }
}