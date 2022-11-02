package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.RegisterUserRequest;
import com.jdawidowska.equipmentRentalService.api.dto.response.UserResponse;
import com.jdawidowska.equipmentRentalService.data.entities.User;
import com.jdawidowska.equipmentRentalService.data.repos.UserRepository;
import com.jdawidowska.equipmentRentalService.exception.UserAlreadyExistsException;
import com.jdawidowska.equipmentRentalService.model.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterUserRequest registerUserRequest) throws UserAlreadyExistsException {
        User user = new User();

        user.setName(registerUserRequest.getName());
        user.setSurname(registerUserRequest.getSurname());
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        user.setRole(Role.USER);

        if (!userRepository.existsByEmail(registerUserRequest.getEmail())) {
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    private UserResponse convertDataIntoDTO(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setSurname(user.getSurname());
        return userResponse;
    }

    public List<UserResponse> findAllUserResponse() {
        return ((List<User>) userRepository.findAll())
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }
}