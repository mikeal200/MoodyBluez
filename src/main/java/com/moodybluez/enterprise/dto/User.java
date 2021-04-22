package com.moodybluez.enterprise.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
public @Data
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private int userId;

    @Basic
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Basic
    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password);
    }
}
