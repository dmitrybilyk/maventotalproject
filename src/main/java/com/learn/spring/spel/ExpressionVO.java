package com.learn.spring.spel;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by dmitry on 1/28/14.
 */
public class ExpressionVO {
    @Value("#{ T(java.lang.Math).random() * 10}")
    private int randomNumber;

    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }
}