package com.learn.patterns.freemanAndFreeman.headfirst.state.custom;

import java.math.BigDecimal;

/**
 * Created by bid on 8/11/14.
 */
public class EmptyState extends State
{
   public EmptyState(CashMachine cashMachine)
   {
      super(cashMachine);
   }

   @Override
   public void fillTheCashMachine(final BigDecimal amountToFill)
   {
      cashMachine.setCurrentAmount(amountToFill);
      cashMachine.setState(cashMachine.getFilledState());
   }

   @Override
   public void withdraw(final BigDecimal amountToWithdraw)
   {
      System.out.println("Please, fill the cash machine!!!");
   }

   @Override
   public void fillTheMobilePhone(final BigDecimal amountToFillMobilePhone)
   {
      System.out.println("Please, fill the cash machine!!!");
   }

   @Override
   public void fixTheCashMachine()
   {
      System.out.println("The cash machine is under the fixing process");
   }

   @Override
   public void turnOnTheCashMachine()
   {
      System.out.println("The cash machine is not fixed");
   }

   @Override
   public void turnOffTheCashMachine()
   {
      System.out.println("The cash machine is not fixed");
   }
}
