package com.sandeepa.user_management_system.controller;

import com.sandeepa.user_management_system.dto.request.CreateUserRequest;
import com.sandeepa.user_management_system.dto.response.UserResponse;
import com.sandeepa.user_management_system.service.UserService;
import com.sandeepa.user_management_system.util.StandardResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getusers")
    public ResponseEntity<StandardResponseDto> getUsers(){
        List<UserResponse> users = userService.getAllUsers();
        return new ResponseEntity<>(
                new StandardResponseDto(
                       true, 200, "Users listed", users
                ), HttpStatus.OK
        );
    }

    @PostMapping("/saveuser")
    public ResponseEntity<StandardResponseDto> saveUser(@Valid @RequestBody CreateUserRequest request){
        UserResponse res = userService.createUser(request);
        return new ResponseEntity<>(
                new StandardResponseDto(
                       true, 200, "User created", res
                ), HttpStatus.OK
        );
    }
}
