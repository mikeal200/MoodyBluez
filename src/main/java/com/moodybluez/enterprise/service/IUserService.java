package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.User;

public interface IUserService {
    User save(User user) throws Exception;

    User fetchByUsername(String username);

    boolean userExists(String username);
}
