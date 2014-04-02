package com.learn.dao.cxfsoap.api;

import com.learn.model.cxfsoap.User;

import java.util.List;

/**
 * Created by dmitry.bilyk on 3/27/14.
 */
public interface UserCxfDao {

    public void addUser(User user);
    public void printMessage();
    List<User> getAllUsers();
}
