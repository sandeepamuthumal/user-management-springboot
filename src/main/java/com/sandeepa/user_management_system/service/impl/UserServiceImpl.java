package com.sandeepa.user_management_system.service.impl;

import com.sandeepa.user_management_system.dto.request.CreateUserRequest;
import com.sandeepa.user_management_system.dto.request.UpdateUserRequest;
import com.sandeepa.user_management_system.dto.response.UserResponse;
import com.sandeepa.user_management_system.exception.DuplicateEmailException;
import com.sandeepa.user_management_system.model.User;
import com.sandeepa.user_management_system.repo.UserRepo;
import com.sandeepa.user_management_system.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
    }

    @Override
    public UserResponse createUser(CreateUserRequest userRequest) {
        if (userRepo.existsByEmail(userRequest.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        User user = modelMapper.map(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setStatus(true);
        userRepo.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserById(Long id) {
        return null;
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest req) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
