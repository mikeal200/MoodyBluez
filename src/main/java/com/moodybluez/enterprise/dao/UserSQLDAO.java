package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"dev", "default"})
public class UserSQLDAO implements IUserDAO {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) throws Exception{
        User createdUser = userRepository.save(user);
        return createdUser;
    }
}
