package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Profile("test")
public class UserDAOStub implements IUserDAO {

    Map<Long, User> allUsers = new HashMap<>();

    public User save(User user) {
        allUsers.put(user.getId(), user);
        return user;
    }
}
