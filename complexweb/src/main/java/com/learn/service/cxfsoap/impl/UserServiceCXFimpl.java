package com.learn.service.cxfsoap.impl;

import com.learn.dao.api.UserDao;
import com.learn.dao.cxfsoap.api.UserCxfDao;
import com.learn.dao.cxfsoap.impl.UserCxfDaoImpl;
import com.learn.model.cxfsoap.User;
import com.learn.service.cxfsoap.api.UserServiceCXF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service
@WebService
public class UserServiceCXFimpl implements UserServiceCXF {

//    @Autowired
    private UserCxfDao userDao = new UserCxfDaoImpl();

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
