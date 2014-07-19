package com.learn.patterns.freemanAndFreeman.headfirst.observer.customobserver.interfacehandmade;

/**
 * Created by dmitry on 7/19/14.
 */
public interface Subject {
    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers();
}
