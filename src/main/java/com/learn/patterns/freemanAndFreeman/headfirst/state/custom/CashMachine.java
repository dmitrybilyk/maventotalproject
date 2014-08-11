package com.learn.patterns.freemanAndFreeman.headfirst.state.custom;

import java.math.BigDecimal;

/**
 * Created by bid on 8/11/14.
 */
public class CashMachine
{
   private EmptyState emptyState;
   private FilledState filledState;
   private FixedState fixedState;
   private TurnedOnState turnedOnState;
   private TurnedOffState turnedOffState;

   private State state;

   private BigDecimal currentAmount;


   public CashMachine(final BigDecimal currentAmount)
   {
      emptyState = new EmptyState(this);
      filledState = new FilledState(this);
      fixedState = new FixedState(this);
      turnedOnState = new TurnedOnState(this);
      turnedOffState = new TurnedOffState(this);

      this.currentAmount = currentAmount;
      state = turnedOnState;
   }

   public BigDecimal getCurrentAmount()
   {
      return currentAmount;
   }

   public void setCurrentAmount(final BigDecimal currentAmount)
   {
      this.currentAmount = currentAmount;
   }

   public void withdraw(final BigDecimal withdrawAmount)
   {
      state.withdraw(withdrawAmount);
   }

   public EmptyState getEmptyState()
   {
      return emptyState;
   }

   public FilledState getFilledState()
   {
      return filledState;
   }

   public FixedState getFixedState()
   {
      return fixedState;
   }

   public TurnedOnState getTurnedOnState()
   {
      return turnedOnState;
   }

   public TurnedOffState getTurnedOffState()
   {
      return turnedOffState;
   }

   public State getState()
   {
      return state;
   }

   public void setState(final State state)
   {
      this.state = state;
   }

   public void fillTheCashMachine(final BigDecimal amountToFill)
   {
      state = filledState;
      state.fillTheCashMachine(amountToFill);
   }

   public void turnOffTheCashMachine()
   {
      state = turnedOffState;
      state.turnOffTheCashMachine();
   }

   public void turnOnTheCashMachine()
   {
      state = turnedOnState;
      state.turnOnTheCashMachine();
   }
}
