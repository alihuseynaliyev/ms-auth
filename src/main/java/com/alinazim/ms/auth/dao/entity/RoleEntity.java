package com.alinazim.ms.auth.dao.entity;

import com.alinazim.ms.auth.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Enumerated(STRING)
    private UserRole name;

}