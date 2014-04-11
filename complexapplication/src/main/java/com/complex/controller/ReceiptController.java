package com.complex.controller;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReceiptController {
    public ModelAndView createReceipt(){
        ModelAndView model = new ModelAndView();
        return model;
    }
}
