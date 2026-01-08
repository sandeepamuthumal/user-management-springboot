package com.sandeepa.user_management_system.service;

import com.sandeepa.user_management_system.dto.request.CreateUserRequest;
import com.sandeepa.user_management_system.dto.request.UpdateUserRequest;
import com.sandeepa.user_management_system.dto.response.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Page<UserResponse> getAllUsers(Boolean status,String userType,String search,int page, int size);
    UserResponse createUser(CreateUserRequest req);
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id, UpdateUserRequest req);
    void deleteUser(Long id);
}
