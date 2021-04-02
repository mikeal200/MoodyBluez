//package com.moodybluez.enterprise.service;
//
//import com.moodybluez.enterprise.dao.IUserDAO;
//import com.moodybluez.enterprise.dto.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService implements IUserService {
//
//    @Autowired
//    IUserDAO userDAO;
//
//    public UserService(IUserDAO userDAO) {
//
//        this.userDAO = userDAO;
//    }
//
//    @Override
//    public User save(User user) throws Exception {
//        return userDAO.save(user);
//    }
//}
