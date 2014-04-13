package com.complex.service;

import com.complex.model.security.User;

import java.util.List;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */
public interface UserService {
    public List<User> getAll();
    public void addUser(User user);
    public User getUserById(int id);
    boolean deleteUser(int id);
    void updateUser(User user);
}
