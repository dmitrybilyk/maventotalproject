package com.learn.service.mvc.impl;

import com.learn.dao.api.UserDao;
import com.learn.dao.impl.UserDaoImpl;
import com.learn.model.cxfsoap.User;
import com.learn.service.mvc.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dmitry.bilyk on 3/27/14.
 */

@Service
public class UserServiceImpl implements UserService {
//    @Autowired
    private UserDao userDao = new UserDaoImpl();


    public void addUser(User user){
        userDao.addUser(user);
    }

    @Override
    public void printMessage() {
        userDao.printMessage();
    }
}
