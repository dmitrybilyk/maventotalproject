package com.learn;

import com.learn.ws.cxf.service.AddUser;
import com.learn.ws.cxf.service.User;
import com.learn.ws.cxf.service.UserService;
import com.learn.ws.cxf.service.impl.UserServiceImplService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmitry.bilyk on 3/27/14.
 */
public class CxfSoapMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/cxf/camel-context.xml");
//        UserService userService = (UserService) applicationContext.getBean("bifUserServiceSOAP");
        System.out.println("fdsafads");

//        UserServiceImplService userService = new UserServiceImplService();
//        AddUser addUser = new AddUser();
//        User user = new User();
//        user.setId(1);
//        user.setName("Dimon");
//        user.setPersonalNumber("2344444");
//        addUser.setArg0(user);
//        userService.getUserServiceImplPort().addUser(user);
    }
}
