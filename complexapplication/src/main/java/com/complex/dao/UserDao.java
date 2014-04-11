package com.complex.dao;

import com.complex.model.security.User;

import java.util.List;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */
public interface UserDao {
    public List<User> getAll();
    public void addUser(User user);
}
