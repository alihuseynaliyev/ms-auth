package com.alinazim.ms.auth.mapper.factory;


import com.alinazim.ms.auth.dao.entity.UserEntity;
import com.alinazim.ms.auth.model.request.RegisterRequest;
import com.alinazim.ms.auth.model.response.UserResponse;

public enum UserMapper {

    USER_MAPPER;

    public UserResponse buildUserResponse(RegisterRequest registerRequest) {
        return UserResponse.builder()
                .username(registerRequest.getUsername())
                .userRole(registerRequest.getRole())
                .build();
    }

    public UserEntity buildUserEntity(RegisterRequest registerRequest, String encodedPassword) {
        return UserEntity.builder()
                .username(registerRequest.getUsername())
                .role(registerRequest.getRole())
                .password(encodedPassword).build();
    }

}
