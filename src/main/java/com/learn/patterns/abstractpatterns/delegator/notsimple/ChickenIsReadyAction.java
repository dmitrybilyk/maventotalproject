package com.learn.patterns.abstractpatterns.delegator.notsimple;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 02.07.12
 * Time: 16:12
 * To change this template use File | Settings | File Templates.
 */
public class ChickenIsReadyAction implements TimerAction {
    public void onTime() {
        System.out.println("Цыплёнок готов!");
    }
}
