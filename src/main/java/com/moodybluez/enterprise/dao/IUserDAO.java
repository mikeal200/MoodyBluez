package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.User;

public interface IUserDAO {
    User save(User user) throws Exception;
}
