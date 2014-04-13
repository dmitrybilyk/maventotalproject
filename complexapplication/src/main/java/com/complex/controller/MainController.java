package com.complex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dmitry on 12.04.14.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String defaultPage(HttpServletRequest request){
        return "login";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "expired")
    public ModelAndView sessionExpired(){
        ModelAndView model = new ModelAndView();
        model.addObject("title", "session is expired");
        model.setView(new JstlView("sessionExpired"));
        return model;
    }

}
