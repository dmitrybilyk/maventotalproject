package com.learn.bases.threads.bankaccounts2;


//When a thread calls an object's synchronized method, the whole object is locked.
//This means that if another thread tries to call any synchronized method of the same
// object, the call will block until the lock is released (which happens when the
// original call finishes). In general, if the value of a field of an object can be
// changed, then all methods that read or write that field should be synchronized to
// prevent two threads from trying to write the field at the same time, and to prevent
// one thread from reading the field while another thread is in the process of writing it.

public class BankAccount {
    private double balance;

    // constructor: set balance to given amount
    public BankAccount( double initialDeposit ) {
        balance = initialDeposit;
    }

    public synchronized double Balance( ) {
        return balance;
    }

    public synchronized void Deposit( double deposit ) {
        balance += deposit;
    }

    public synchronized void Withdraw( double withdrawal ) {
        balance -= withdrawal;
    }

}