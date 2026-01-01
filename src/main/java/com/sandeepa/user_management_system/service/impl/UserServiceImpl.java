package com.sandeepa.user_management_system.service.impl;

import com.sandeepa.user_management_system.dto.request.CreateUserRequest;
import com.sandeepa.user_management_system.dto.request.UpdateUserRequest;
import com.sandeepa.user_management_system.dto.response.UserResponse;
import com.sandeepa.user_management_system.exception.DuplicateEmailException;
import com.sandeepa.user_management_system.exception.EntryNotFoundException;
import com.sandeepa.user_management_system.model.User;
import com.sandeepa.user_management_system.model.UserType;
import com.sandeepa.user_management_system.repo.UserRepo;
import com.sandeepa.user_management_system.repo.UserTypeRepo;
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

    private final UserTypeRepo userTypeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserTypeRepo userTypeRepo) {
        this.userTypeRepo = userTypeRepo;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepo.findAllByOrderByCreatedAtDesc();
        return users.stream().map(user -> {
            UserResponse res = new UserResponse();
            res.setId(user.getId());
            res.setName(user.getName());
            res.setEmail(user.getEmail());
            res.setStatus(user.isStatus());
            res.setUserType(user.getUserType().getName());
            res.setCreatedAt(user.getCreatedAt());
            res.setUpdatedAt(user.getUpdatedAt());
            return res;
        }).collect(Collectors.toList());
    }

    @Override
    public UserResponse createUser(CreateUserRequest userRequest) {
        if (userRepo.existsByEmail(userRequest.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        UserType userType = userTypeRepo.findById(userRequest.getUserTypeId()).orElseThrow(() -> new EntryNotFoundException("User type not found"));

        User user = new User(); // <-- IMPORTANT
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setStatus(true);
        user.setUserType(userType);

        User savedUser = userRepo.save(user);

        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new EntryNotFoundException("User not found"));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest req) {
        User existing = userRepo.findById(id).orElseThrow(()-> new EntryNotFoundException("User not found"));

        //check email duplication
        if(!existing.getEmail().equals(req.getEmail())) {
            if(userRepo.existsByEmail(req.getEmail())) {
                throw new DuplicateEmailException("Email already exists");
            }
        }

        if(req.getName() != null) existing.setName(req.getName());
        if(req.getEmail() != null) existing.setEmail(req.getEmail());
        if(req.getStatus() != null) existing.setStatus(req.getStatus());
        if(req.getUserTypeId() != null) {
            UserType userType = userTypeRepo.findById(req.getUserTypeId()).orElseThrow(() -> new EntryNotFoundException("User type not found"));
            existing.setUserType(userType);
        }

        User saved  = userRepo.save(existing);
        return modelMapper.map(saved, UserResponse.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
