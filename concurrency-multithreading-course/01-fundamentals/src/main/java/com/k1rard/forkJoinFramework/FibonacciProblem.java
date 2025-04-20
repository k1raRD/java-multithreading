package com.k1rard.forkJoinFramework;

import java.util.concurrent.RecursiveTask;

public class FibonacciProblem extends RecursiveTask<Integer> {

    private int n;

    public FibonacciProblem(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        // F(0) = F(1) = 0
        if(n <= 1)
            return n;

        FibonacciProblem fib1 = new FibonacciProblem(n - 1);
        FibonacciProblem fib2 = new FibonacciProblem(n - 2);

        fib1.fork();
        fib2.fork();

        return fib1.join() + fib2.join();
    }
}
