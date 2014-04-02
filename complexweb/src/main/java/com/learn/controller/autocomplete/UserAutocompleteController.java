package com.learn.controller.autocomplete;

import java.util.List;

import com.learn.model.mvc.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "users")
public class UserAutocompleteController {

    private static DummyDB dummyDB = new DummyDB();

    @RequestMapping(value="/saveInfo", method=RequestMethod.POST)
    @ResponseBody
    public User createSmartphone(@RequestBody User user) {
        return user;
    }


    @RequestMapping(value = "/startautocomplete", method = RequestMethod.GET)
    public ModelAndView index() {

        User userForm = new User();

        return new ModelAndView("autocomplete_users", "userForm", userForm);
    }

    @RequestMapping(value = "/get_country_list",
            method = RequestMethod.GET,
            headers="Accept=*/*")
    public @ResponseBody List<String> getCountryList(@RequestParam("term") String query) {
        List<String> countryList = dummyDB.getCountryList(query);

        return countryList;
    }

    @RequestMapping(value = "/get_tech_list",
            method = RequestMethod.GET,
            headers="Accept=*/*")
    public @ResponseBody List<String> getTechList(@RequestParam("term") String query) {
        List<String> countryList = dummyDB.getTechList(query);

        return countryList;
    }

//    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
//    public void saveUserInfo(@ModelAttribute("userForm") User user,
//                             BindingResult result){
//        System.out.println("fdsfds");
//    }

}