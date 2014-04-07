package com.learn.bases.threads.printNumbers;

class PrintSum extends Thread {
    // run method prints, for each k in the range start..end, the sum
    // of the even numbers from 1 to k if "even" is true; otherwise,
    // it prints the sum of the odd numbers from 1 to k

    // private data members
    int start, end;
    boolean even;    // true means print sum of even numbers,
                     // false means print sum of odd numbers

    // constructor: set start, end, and even
    public PrintSum(int s, int e, boolean ev) {
        start = s;
	end = e;
	even = ev;
    }

    // run method; print sums of even or odd numbers depending on
    // the value of "even"
    public void run() {
        for (int k = start; k <= end; k++) {
            int sum = 0;
            for (int j = 1; j <= k; j++) {
                if (even && j / 2 * 2 == j) {
                    sum += j;
                } else if (!even && j / 2 * 2 != j) {
                    sum += j;
                }
            }
            if (even) {
                System.out.println("even sum: " + sum);
            } else {
                System.out.println("odd sum: " + sum);
            }

//             The call to sleep causes the thread to wait for 100 milliseconds
// (1/10 of a second) after each iteration of the loop. However, this code is not
// quite correct: the sleep method can throw an InterruptedException, and a
// thread's run method is not allowed to throw any exceptions (because Thread.run,
// which is being overridden, throws no exceptions). Therefore, we must add code to
// catch that exception:
            try {
                sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}