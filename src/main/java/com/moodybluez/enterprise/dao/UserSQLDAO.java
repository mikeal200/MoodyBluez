package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Repository
@Profile({"dev", "default"})
@Service

/**
 * @param user the user that is logged into the app
 *
 */
public class UserSQLDAO implements IUserDAO {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public User fetchByUsername(String username){
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public Map<Integer, User> fetchAll() {
        Map<Integer, User> entities = new HashMap<>();
        userRepository.findAll().forEach(entry -> {
            entities.put(entry.getUserId(),entry);
        });
        return entities;
    }
}
