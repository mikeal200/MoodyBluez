package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dao.IUserDAO;
import com.moodybluez.enterprise.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    public UserService() {

    }

    public UserService(IUserDAO userDAO) {

        this.userDAO = userDAO;
    }

    @Override
    public User save(User user) throws Exception {
        if(userExists(user.getUsername())) {
            throw new Exception("There is an account with that email address: "
                    + user.getUsername());
        }
        return userDAO.save(user);
    }

    public boolean userExists(String username) {
        return userDAO.fetchByUsername(username) != null;
    }

    public User fetchByUsername(String username) {
        return userDAO.fetchByUsername(username);
    }
}
