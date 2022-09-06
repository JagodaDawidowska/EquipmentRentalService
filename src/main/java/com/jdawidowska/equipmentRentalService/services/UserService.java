package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.UserRequest;
import com.jdawidowska.equipmentRentalService.data.entities.User;
import com.jdawidowska.equipmentRentalService.data.repos.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean addUser(UserRequest userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());

        if(!userRepository.existsByEmail(userRequest.getEmail())){
            userRepository.save(user);
            return true;
        }else return false;
    }
}