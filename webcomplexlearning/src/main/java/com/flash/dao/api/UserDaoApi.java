package com.flash.dao.api;

import com.flash.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 31.08.13
 * Time: 18:57
 * To change this template use File | Settings | File Templates.
 */
public interface UserDaoApi {
    User getUserById(int userId);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(int userId);

    void addUser(User user);
}
