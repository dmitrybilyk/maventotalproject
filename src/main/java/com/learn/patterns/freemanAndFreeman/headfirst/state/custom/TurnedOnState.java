package com.learn.patterns.freemanAndFreeman.headfirst.state.custom;

import java.math.BigDecimal;

/**
 * Created by bid on 8/11/14.
 */
public class TurnedOnState extends State
{

   public TurnedOnState(CashMachine cashMachine)
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

}
