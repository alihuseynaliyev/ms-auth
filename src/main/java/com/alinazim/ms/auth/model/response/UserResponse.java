package com.alinazim.ms.auth.model.response;

import com.alinazim.ms.auth.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String username;
    private UserRole userRole;
}
