package com.complex.service.impl;

import com.complex.dao.UserDao;
import com.complex.service.UserService;
import com.complex.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
