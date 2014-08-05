package com.learn.patterns.freemanAndFreeman.headfirst.templatemethod.custom;

/**
 * Created by dmitry on 8/4/14.
 */
public class Main {
    public static void main(String[] args) {
        JuiceTemplateMethodHolder juiceTemplateMethod = new AppleJuiceTemplateMethodHolder();
//        JuiceTemplateMethodHolder juiceTemplateMethod = new LimonJuiceTemplateMethodHolder();
        juiceTemplateMethod.createAJuice();
    }
}
