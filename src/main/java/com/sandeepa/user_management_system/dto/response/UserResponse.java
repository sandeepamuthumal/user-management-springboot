package com.sandeepa.user_management_system.dto.response;

import lombok.Data;

import java.time.Instant;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Boolean status;
    private Instant createdAt;
    private Instant updatedAt;
}
