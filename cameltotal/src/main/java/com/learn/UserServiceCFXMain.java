package com.learn;

import com.learn.service.cxfsoap.User;
import com.learn.service.cxfsoap.impl.UserServiceCXFimplService;

/**
 * Created by dmitry.bilyk on 3/28/14.
 */
public class UserServiceCFXMain {
    public static void main(String[] args) {
        UserServiceCXFimplService service = new UserServiceCXFimplService();
        User user = new User();
        user.setFirstName("TESTSOAP");
        service.getUserServiceCXFimplPort().addUser(user);
        System.out.println("fdsfds");
    }
}
