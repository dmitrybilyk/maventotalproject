package com.learn.service.cxfsoap.cxfclient;



import com.learn.model.cxfsoap.User;
import com.learn.service.cxfsoap.api.UserServiceCXF;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public final class Client {

    public Client() {
    }

    public static void main(String args[]) throws Exception {
         ClassPathXmlApplicationContext context 
            = new ClassPathXmlApplicationContext(new String[] {"cxf-client-spring/client-beans.xml"});

        UserServiceCXF client = (UserServiceCXF) context.getBean("userServiceCXF");
		User user = new User();
		user.setFirstName("Diiiiiiiiima");
        user.setLastName("Biiiiiiiilyk");
        user.setAge(322);

        client.addUser(user);

        List<User> userList = client.getAllUsers();
        for (User user1 : userList) {
            System.out.println(user1.getFirstName());
        }
            
    }
}
