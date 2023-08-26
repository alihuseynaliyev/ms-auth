package com.alinazim.ms.auth.model.response;

import com.alinazim.ms.auth.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {
    private String username;
    private Set<UserRole> userRole;
}
