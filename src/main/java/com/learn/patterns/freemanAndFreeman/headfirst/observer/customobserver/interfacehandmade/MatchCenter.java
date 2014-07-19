package com.learn.patterns.freemanAndFreeman.headfirst.observer.customobserver.interfacehandmade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 7/19/14.
 */
@Component("matchCenter")
public class MatchCenter implements Subject {

    private List<Observer> observers;

    @Autowired
    private Result result;

    public MatchCenter() {
        this.observers = new ArrayList<Observer>();
    }

//    public void init() {
//        this.observers = new ArrayList<Observer>();
//    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(result);
        }
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
        resultChanged();
    }

    private void resultChanged() {
        notifyObservers();
    }
}
