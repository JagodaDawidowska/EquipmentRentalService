package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.RegisterUserRequest;
import com.jdawidowska.equipmentRentalService.data.repos.InventoryRepository;
import com.jdawidowska.equipmentRentalService.data.repos.UserRepository;
import com.jdawidowska.equipmentRentalService.exception.ItemNotFoundException;
import com.jdawidowska.equipmentRentalService.exception.UserNotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService systemUnderTest;

    @Mock
    private UserRepository userRepository;

    @SneakyThrows
    @Test
    void removeItem_should_throw_exception_when_entity_missing() {
        Mockito
                .when(userRepository.existsByEmail(any()))
                .thenReturn(true);

        assertThrows(UserNotFoundException.class,
                () -> systemUnderTest.registerUser(new RegisterUserRequest(
                "",
                "",
                "",
                "",
                ""
        )));
    }
}