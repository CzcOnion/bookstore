package org.csu.bookstore.service.implement;

import org.csu.bookstore.domain.User;
import org.csu.bookstore.persistence.UserMapper;
import org.csu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(int userId){
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User getUser(int userId, String password) {
        return null;
    }


    @Override
    public void insertUser(User user) {
        try {
            user.setUserId(userMapper.getNextId());
        }
        catch (Exception e){
            int userId = 1000000;
            user.setUserId(userId);
        }
        userMapper.insert(user);
    }

    @Override
    public void UpdateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void updatePassword(User user) {
        if (user.getPassword() != null && user.getPassword().length() > 0) {
            userMapper.updateByPrimaryKey(user);
        }
    }
}
