package com.alinazim.ms.auth.model.request;

import com.alinazim.ms.auth.model.enums.UserRole;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Set<UserRole> role;
}
