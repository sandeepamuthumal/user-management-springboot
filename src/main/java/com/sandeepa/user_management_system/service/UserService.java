package com.sandeepa.user_management_system.service;

import com.sandeepa.user_management_system.dto.request.CreateUserRequest;
import com.sandeepa.user_management_system.dto.request.UpdateUserRequest;
import com.sandeepa.user_management_system.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse createUser(CreateUserRequest req);
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id, UpdateUserRequest req);
    void deleteUser(Long id);
}
