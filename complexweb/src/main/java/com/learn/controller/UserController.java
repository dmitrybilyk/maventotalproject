package com.learn.controller;

import com.learn.model.cxfsoap.User;
import com.learn.service.mvc.api.UserService;
import com.learn.service.mvc.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dmitry.bilyk on 3/27/14.
 */
@Controller
public class UserController {

//    @Autowired
    private UserService userService = new UserServiceImpl();


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getdata() {

        ModelAndView model = new ModelAndView("CustomerForm");

        return model;

    }

    @RequestMapping(value= "/addUser")
    public String addUser(HttpServletRequest request, HttpServletResponse response){
//    public String addUser(@PathVariable("userName") String userName,
//                          @PathVariable("userLastName") String userLastName){
        User user = new User();
        user.setLastName(request.getParameter("UserLastName"));
        user.setFirstName(request.getParameter("UserName"));
        userService.addUser(user);
        return "users";
    }


    @RequestMapping(value = "/print")
    public String printMessage(){
        userService.printMessage();
        return "testView";
    }

}
