package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.User;

import java.util.Map;

/**
 * allows user to enter details about themself
 *
 * @param username the name the user chooses for themself
 */
public interface IUserDAO {
    User save(User user);

    User fetchByUsername(String username);

    Map<Integer, User> fetchAll();
}
