package com.learn.patterns.freemanAndFreeman.headfirst.templatemethod.custom;

/**
 * Created by dmitry on 8/4/14.
 */
public class AppleJuiceTemplateMethodHolder extends JuiceTemplateMethodHolder {
    @Override
    protected void squizeFruits() {
        System.out.println("We are squizing apples");
    }

    @Override
    protected void nakeFruits() {
        System.out.println("We are naking the apples. But it's not strictly required actually");
    }

    @Override
    protected void buyFruits() {
        System.out.println("We are buying the apples");
    }

    @Override
    protected void washHandsHook(){
        System.out.println("We are washing our hands because it's apples...");
    }
}
