package com.sandeepa.user_management_system.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @Size(max = 100, message = "Name must be less than 100 characters.")
    private String name;

    @Email(message = "Invalid email format.")
    private String email;

    private Boolean status;
}
