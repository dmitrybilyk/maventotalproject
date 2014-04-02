package com.learn.bases.threads.bankaccounts;

/**
 * Created by dmitry on 1/31/14.
 */
public class Main {
    public static void main(String[] args) {
        BankAccountRunnable bankAccountRunnable = new BankAccountRunnable();
        for (int i = 0; i<2; i++){
            Thread thread = new Thread(bankAccountRunnable);
            thread.setName("Thread "+i);
            thread.start();
        }
    }
}
