package com.learn.patterns.freemanAndFreeman.headfirst.state.custom;

import java.math.BigDecimal;

/**
 * Created by bid on 8/11/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      CashMachine cashMachine = new CashMachine(new BigDecimal(0));
      cashMachine.fillTheCashMachine(new BigDecimal(5000));

      System.out.println(cashMachine.getCurrentAmount());

      cashMachine.turnOffTheCashMachine();

      cashMachine.fillTheCashMachine(new BigDecimal(1000));

      cashMachine.turnOnTheCashMachine();

      cashMachine.withdraw(new BigDecimal(4000));

      cashMachine.withdraw(new BigDecimal(1000));

      cashMachine.withdraw(new BigDecimal(1000));

      cashMachine.withdraw(new BigDecimal(1000));

      cashMachine.fillTheCashMachine(new BigDecimal(10000));

      cashMachine.withdraw(new BigDecimal(4000));
   }
}
