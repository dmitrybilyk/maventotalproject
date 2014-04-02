package com.learn.dao.api;

import com.learn.model.cxfsoap.User;

import java.util.List;

/**
 * Created by dmitry.bilyk on 3/27/14.
 */
public interface UserDao {

    public void addUser(User user);
    public void printMessage();
    List<User> getAllUsers();
}
