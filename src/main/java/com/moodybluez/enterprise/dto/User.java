package com.moodybluez.enterprise.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public @Data
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 45)
    private String userName;

    @Column(nullable = false, length = 64)
    private String password;
}
