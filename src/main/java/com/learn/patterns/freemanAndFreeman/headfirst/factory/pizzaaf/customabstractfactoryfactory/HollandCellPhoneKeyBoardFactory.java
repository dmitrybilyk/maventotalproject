package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzaaf.customabstractfactoryfactory;

/**
 * Created by bid on 8/1/14.
 */
public class HollandCellPhoneKeyBoardFactory implements KeyboardFactory
{
   @Override
   public Keyboard createKeyboard()
   {
      return new HollandKeyboard();
   }
}
