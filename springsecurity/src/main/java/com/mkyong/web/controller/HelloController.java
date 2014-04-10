package com.mkyong.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HelloController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {

        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        return model;

    }


    @PreAuthorize("ROLE_FLASH")
    @RequestMapping(value = {"/preautho" }, method = RequestMethod.GET)
    public ModelAndView preAuthorizeTest(Principal principal) {

        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = {"/403" }, method = RequestMethod.GET)
    public ModelAndView error403() {

        ModelAndView model = new ModelAndView();
        model.setViewName("403");
        return model;

    }


    @Secured("ROLE_FLASH")
	@RequestMapping(value = {"/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;

	}

    @Secured("ROLE_FLASH")
    @RequestMapping(value = {"/seccheck" }, method = RequestMethod.GET)
    public ModelAndView securedCheck() {

        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        return model;

    }

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");

		return model;

	}

//    @Secured("")
    @RequestMapping(value = "/superadmin**", method = RequestMethod.GET)
    public ModelAndView superAdminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Custom Login Form of super admin");
        model.addObject("message", "This is protected page of super admin!");
        model.setViewName("superadmin");

        return model;

    }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public ModelAndView login2(@RequestParam(value = "error2", required = false) String error2,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error2 != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login2");

        return model;

    }

}