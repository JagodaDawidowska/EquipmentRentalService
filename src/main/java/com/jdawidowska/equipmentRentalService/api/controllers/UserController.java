package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.request.AddUserRequest;
import com.jdawidowska.equipmentRentalService.api.dto.response.RegisterEnum;
import com.jdawidowska.equipmentRentalService.api.dto.response.RegisterResponse;
import com.jdawidowska.equipmentRentalService.api.dto.response.UserResponse;
import com.jdawidowska.equipmentRentalService.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody AddUserRequest addUserRequest){
        if( userService.addUser(addUserRequest)){
            RegisterResponse correctResponse = new RegisterResponse(RegisterEnum.USER_REGISTED);
            return new ResponseEntity<>(correctResponse,HttpStatus.OK );
        }else {
            RegisterResponse errorResponse = new RegisterResponse(RegisterEnum.EMAIL_ALREADY_EXISTS);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public List<UserResponse> findAllUserResponse(){
        return userService.findAllUserResponse();
    }
}