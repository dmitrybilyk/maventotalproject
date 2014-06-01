package com.learn.patterns.abstractpatterns.delegator.notsimple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 02.07.12
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 */
public class Timer {
    TimerAction action;

    /**
     * Функция, которую вызывает программист для установки времени.
     */
    void run() {
        if (isTime()) {
            action.onTime();
        }
    }

    /**
     * Некоторая функция, которая берет на себя всю работу со временем. Ее
     * реализация не интересна в данном контексте.
     *
     * @return
     */
    private boolean isTime() {
        return true;
    }

    public static void main(String[] args) {

        System.out.println("Введите тип действия:");
//        String actionType = System.console().readLine();
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String actionType = null;
        try {
            actionType = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Timer timer = new Timer();

        if (actionType.equalsIgnoreCase("set wake up spring.timer")) {
            timer.action = new WakeUpAction();
        } else if (actionType.equalsIgnoreCase("set chicken spring.timer")) {
            timer.action = new ChickenIsReadyAction();
        }

        timer.run();
}
}
