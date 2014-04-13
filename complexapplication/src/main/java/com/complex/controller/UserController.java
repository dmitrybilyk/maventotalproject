package com.complex.controller;

import com.complex.model.security.User;
import com.complex.model.security.UserRole;
import com.complex.model.security.UserRoleType;
import com.complex.service.UserRoleService;
import com.complex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/usersList")
    public ModelAndView goToUsersList(){
        ModelAndView model = new ModelAndView();
        model.setViewName("usersList");
        model.addObject("users", userService.getAll());
        return model;
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView goToEditPage(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView();
        model.setViewName("userEditAdd");
        model.addObject("user", userService.getUserById(id));
        return model;
    }

    @RequestMapping(value = "/add")
    public ModelAndView goToAddPage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("userEditAdd");
        model.addObject("user", new User());
        return model;
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user,
                           BindingResult result, Model model){
//        user.setUserRole(userRoleService.getUserRoleById(userRoleId));
        userService.addUser(user);
        model.addAttribute("users", userService.getAll());
        return "usersList";
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


    @ModelAttribute("userRoles")
    public Map<String, String> populateUserRoles() {

        //Data referencing for user roles options

        Map<String, String> userRoleMap = null;

        List<UserRole> userRoles = userRoleService.getAll();

        for (UserRole userRole : userRoles) {
            userRoleMap.put(userRole.getAuthority(), userRole.getAuthority());
        }

        return userRoleMap;
    }

}
