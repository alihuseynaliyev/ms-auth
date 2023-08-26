package com.alinazim.ms.auth.util;

import com.alinazim.ms.auth.dao.entity.RoleEntity;
import com.alinazim.ms.auth.dao.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.stream.Collectors;

public enum JwtUtil {
    JWT_UTIL;

    public String generateToken(UserEntity user, String secretKey) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000);

        var roleNames = user.getRoles().stream()
                .map(RoleEntity::getName)
                .map(Enum::name)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("roles", roleNames)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
