package com.learn.patterns.freemanAndFreeman.headfirst.state.custom;

import java.math.BigDecimal;

/**
 * Created by bid on 8/11/14.
 */
public abstract class State
{

   CashMachine cashMachine;

   protected State(final CashMachine cashMachine)
   {
      this.cashMachine = cashMachine;
   }

   public void fillTheCashMachine(BigDecimal amountToFill){
      System.out.println("Filled the cash machine");
      cashMachine.setCurrentAmount(cashMachine.getCurrentAmount().add(amountToFill));
   }
   public void withdraw(BigDecimal amountToWithdraw){
      if (cashMachine.getCurrentAmount().compareTo(amountToWithdraw) >= 0){
         cashMachine.setCurrentAmount(cashMachine.getCurrentAmount().subtract(amountToWithdraw));
         System.out.println("Withdrawn - " + amountToWithdraw);
         if (cashMachine.getCurrentAmount().compareTo(new BigDecimal(0)) == 0){
            cashMachine.setState(new EmptyState(cashMachine));
         }
      } else {
         System.out.println("The sum is not enough to withdraw");
      }
   }
   public void fillTheMobilePhone(BigDecimal amountToFillMobilePhone){
      System.out.println("Filled the mobile");
      cashMachine.setCurrentAmount(cashMachine.getCurrentAmount().add(amountToFillMobilePhone));
   }
   public void fixTheCashMachine(){
      System.out.println("Fixed the cash machine");
   }
   public void turnOnTheCashMachine(){
      System.out.println("turned on the cash machine");
   }
   public void turnOffTheCashMachine(){
      System.out.println("turned off the cash machine");
   }

}
