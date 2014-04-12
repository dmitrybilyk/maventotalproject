package com.complex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by dmitry on 12.04.14.
 */
@Controller
@RequestMapping(value = "/session")
public class MainController {

    @RequestMapping(value = "expired")
    public ModelAndView sessionExpired(){
        ModelAndView model = new ModelAndView();
        model.addObject("title", "session is expired");
        model.setView(new JstlView("sessionExpired"));
        return model;
    }

}
