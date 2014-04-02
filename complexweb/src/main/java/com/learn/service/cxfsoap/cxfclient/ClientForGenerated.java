package com.learn.service.cxfsoap.cxfclient;

import com.learn.service.cxfsoap.User;
import com.learn.service.cxfsoap.impl.UserServiceCXFimplService;

import java.util.List;

/**
 * Created by dmitry.bilyk on 3/31/14.
 */
public class ClientForGenerated {
    public static void main(String[] args) {
        UserServiceCXFimplService userServiceCXFimplService = new UserServiceCXFimplService();
        List<User> users = userServiceCXFimplService.getUserServiceCXFimplPort().getAllUsers();
        System.out.println(users);
    }
}
