package com.learn.service.cxfsoap.api;

import com.learn.model.cxfsoap.User;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface UserServiceCXF {
    public void addUser(User user);
    public List<User> getAllUsers();
}
