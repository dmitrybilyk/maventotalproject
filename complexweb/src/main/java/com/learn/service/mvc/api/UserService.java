package com.learn.service.mvc.api;

import com.learn.model.cxfsoap.User;

/**
 * Created by dmitry.bilyk on 3/27/14.
 */
public interface UserService {

    public void addUser(User user);
    public void printMessage();
}
