package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.dto.User;

import java.util.Map;

public interface IUserDAO {
    User save(User user);

    User fetchByUsername(String username);

    Map<Integer, User> fetchAll();
}
