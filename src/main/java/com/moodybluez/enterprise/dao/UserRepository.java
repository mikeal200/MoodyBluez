package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("!test")
public interface UserRepository extends JpaRepository<User, Long> {
}
