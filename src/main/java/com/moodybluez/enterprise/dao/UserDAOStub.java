package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("test")
public class UserDAOStub implements IUserDAO {

    Map<Integer, User> allUsers = new HashMap<>();

    public User save(User user) {
        allUsers.put(user.getUserId(), user);
        return allUsers.get(user.getUserId());
    }

    public User fetchByUsername(String username){
        List<User> userList = new ArrayList<>(allUsers.values());
        User matchedUser = new User();

        for (User userDTO : userList) {
            if (userDTO.getUsername() == username) {
                matchedUser = userDTO;
            }
        }

        return matchedUser;
    }

    public Map<Integer, User> fetchAll(){
        return allUsers;
    }
}
