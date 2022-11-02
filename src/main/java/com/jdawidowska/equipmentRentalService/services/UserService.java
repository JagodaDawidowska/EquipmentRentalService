package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.AddUserRequest;
import com.jdawidowska.equipmentRentalService.api.dto.response.UserResponse;
import com.jdawidowska.equipmentRentalService.data.entities.User;
import com.jdawidowska.equipmentRentalService.data.repos.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean addUser(AddUserRequest addUserRequest){
        User user = new User();
        user.setName(addUserRequest.getName());
        user.setSurname(addUserRequest.getSurname());
        user.setEmail(addUserRequest.getEmail());
        user.setPassword(addUserRequest.getPassword());
        user.setRole(addUserRequest.getRole());

        if(!userRepository.existsByEmail(addUserRequest.getEmail())){
            userRepository.save(user);
            return true;
        } else return false;
    }

    private UserResponse convertDataIntoDTO (User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setSurname(user.getSurname());
        return userResponse;
    }

    public List<UserResponse> findAllUserResponse(){
        return ((List<User>) userRepository.findAll())
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }
}