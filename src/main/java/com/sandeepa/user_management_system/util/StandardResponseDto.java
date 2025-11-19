package com.sandeepa.user_management_system.util;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardResponseDto {
    private boolean success;
    private int code;
    private String message;
    private Object data;
}
