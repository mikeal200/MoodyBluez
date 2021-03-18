package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("!test")
public interface UserRepository extends CrudRepository<User, Long> {
}
