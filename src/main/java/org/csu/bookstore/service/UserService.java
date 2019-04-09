package org.csu.bookstore.service;

import org.csu.bookstore.domain.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    User getUser(int userId);

    User getUser(int userId,String password);

    @Transactional
    void insertUser(User user);

    @Transactional
    void UpdateUser(User user);

    @Transactional
    void updatePassword(User user);
}
