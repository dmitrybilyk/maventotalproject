package com.complex.controller;

import com.complex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String defaultPage(){
        return "login";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/welcome")
    public ModelAndView welcome(){
        ModelAndView model = new ModelAndView();
        model.addObject("message", "my success");
//        userService.addUser(new User("name", "name"));
        model.addObject("users", userService.getAll());
        model.setViewName("users");
        return model;
    }

}
