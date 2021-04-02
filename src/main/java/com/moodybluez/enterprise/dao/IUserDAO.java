package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.dto.User;

import java.util.Map;

public interface IUserDAO {
    User saveEntry(User user);

    User fetchByUsername(String username);

    Map<Integer, User> fetchAll();
}
