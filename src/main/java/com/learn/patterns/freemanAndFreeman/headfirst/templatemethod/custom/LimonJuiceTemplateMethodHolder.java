package com.learn.patterns.freemanAndFreeman.headfirst.templatemethod.custom;

/**
 * Created by dmitry on 8/4/14.
 */
public class LimonJuiceTemplateMethodHolder extends JuiceTemplateMethodHolder {
    @Override
    protected void squizeFruits() {
        System.out.println("We are squizing limon with hands");
    }

    @Override
    protected void nakeFruits() {
        System.out.println("We are naking the fruits. but really we only cutting them");
    }

    @Override
    protected void buyFruits() {
        System.out.println("We are buying the limons");
    }
}
