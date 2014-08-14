package com.learn.patterns.freemanAndFreeman.headfirst.combining.customcombining;

import java.util.ArrayList;

/**
 * Created by bid on 8/14/14.
 */
public interface Observable
{
   ArrayList<Observer> observers = new ArrayList<>();

   public void registerObserver(Observer observer);

   public void notifyObservers();

}
