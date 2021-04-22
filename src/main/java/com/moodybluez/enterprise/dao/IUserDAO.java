package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.User;

import java.util.Map;

public interface IUserDAO {

    /**
     *
     * @param user user that will be saved into the database
     * @return returns user that was saved into database
     */
    User save(User user);

    /**
     *
     * @param username string of username that will be fetched
     * @return returns single user that was fetched by name
     */
    User fetchByUsername(String username);

    /**
     *
     * @return returns map of every user in the database
     */
    Map<Integer, User> fetchAll();
}
