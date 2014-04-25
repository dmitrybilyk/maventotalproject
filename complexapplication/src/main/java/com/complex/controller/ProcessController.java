package com.complex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dmitry on 20.04.14.
 */
@Controller
public class ProcessController {
    @RequestMapping(value = "/test")
    public void print(){
        System.out.println("tets name");
    }
}
