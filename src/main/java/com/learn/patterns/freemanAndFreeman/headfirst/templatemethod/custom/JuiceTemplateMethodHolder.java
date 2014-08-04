package com.learn.patterns.freemanAndFreeman.headfirst.templatemethod.custom;

/**
 * Created by dmitry on 8/4/14.
 */
public abstract class JuiceTemplateMethodHolder {
    protected final void createAJuice(){
        buyFruits();
        washHandsHook();
        nakeFruits();
        squizeFruits();
        fillTheGlass();
    }

    protected void washHandsHook() {
        System.out.println("we may wash our hands. But it's optional may be");
    }

    private void fillTheGlass() {
        System.out.println("We are filling the glass to drink");
    }

    protected abstract void squizeFruits();

    protected abstract void nakeFruits();

    protected abstract void buyFruits();
}
