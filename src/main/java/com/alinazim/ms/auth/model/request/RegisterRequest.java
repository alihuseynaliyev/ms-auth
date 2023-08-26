package com.alinazim.ms.auth.model.request;

import com.alinazim.ms.auth.model.enums.UserRole;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private UserRole role;
}
