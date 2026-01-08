package com.sandeepa.user_management_system.controller;

import com.sandeepa.user_management_system.dto.request.CreateUserRequest;
import com.sandeepa.user_management_system.dto.request.UpdateUserRequest;
import com.sandeepa.user_management_system.dto.response.UserResponse;
import com.sandeepa.user_management_system.service.UserService;
import com.sandeepa.user_management_system.util.StandardResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<StandardResponseDto> getUsers(
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) String userType,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<UserResponse> users = userService.getAllUsers(status, userType, search,page,size);

        return new ResponseEntity<>(
                new StandardResponseDto(
                       true, 200, "Users listed", users
                ), HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity<StandardResponseDto> saveUser(@Valid @RequestBody CreateUserRequest request){
        UserResponse res = userService.createUser(request);
        return new ResponseEntity<>(
                new StandardResponseDto(
                       true, 200, "User created", res
                ), HttpStatus.CREATED
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StandardResponseDto> getUserById(@PathVariable Long id){
        UserResponse res = userService.getUserById(id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        true, 200, "User found", res
                ), HttpStatus.OK
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StandardResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request){
        UserResponse res = userService.updateUser(id, request);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        true, 200, "User updated", res
                ), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponseDto> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        true, 200, "User deleted", null
                ), HttpStatus.OK
        );
    }


}
