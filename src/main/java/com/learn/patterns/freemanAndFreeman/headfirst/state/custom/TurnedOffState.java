package com.learn.patterns.freemanAndFreeman.headfirst.state.custom;

import java.math.BigDecimal;

/**
 * Created by bid on 8/11/14.
 */
public class TurnedOffState extends State
{

   public TurnedOffState(CashMachine cashMachine)
   {
      super(cashMachine);
   }

   public CashMachine getCashMachine()
   {
      return cashMachine;
   }

   public void setCashMachine(final CashMachine cashMachine)
   {
      this.cashMachine = cashMachine;
   }

   @Override
   public void fillTheCashMachine(final BigDecimal amountToFill)
   {
      System.out.println("Please, turn on the cash machine in order to fill it");
   }

   @Override
   public void withdraw(final BigDecimal amountToWithdraw)
   {
      cashMachine.setCurrentAmount(cashMachine.getCurrentAmount().subtract(amountToWithdraw));
   }

   @Override
   public void fillTheMobilePhone(final BigDecimal amountToFillMobilePhone)
   {
      cashMachine.setCurrentAmount(cashMachine.getCurrentAmount().subtract(amountToFillMobilePhone));
   }

   @Override
   public void fixTheCashMachine()
   {
      System.out.println("We've currently fixed the cash machine");
   }

   @Override
   public void turnOnTheCashMachine()
   {
      System.out.println("We are currently turning on the cash machine");
   }

   @Override
   public void turnOffTheCashMachine()
   {
      System.out.println("We are currently turning off the cash machine");
   }
}
