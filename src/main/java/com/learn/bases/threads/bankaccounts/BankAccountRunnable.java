package com.learn.bases.threads.bankaccounts;

/**
 * Created by dmitry on 1/31/14.
 */
public class BankAccountRunnable implements Runnable{
    BankAccount bankAccount = new BankAccount();
    @Override
    public void run() {
        bankAccount.deposit(100);
        System.out.println(bankAccount.accountBalance);
        System.out.println(Thread.currentThread().getName());
    }
}
