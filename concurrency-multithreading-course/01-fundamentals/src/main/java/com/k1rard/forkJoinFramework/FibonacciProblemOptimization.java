package com.k1rard.forkJoinFramework;

import java.util.concurrent.RecursiveTask;

public class FibonacciProblemOptimization extends RecursiveTask<Integer> {

    private int n;

    public FibonacciProblemOptimization(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        // F(0) = F(1) = 0
        if(n <= 1)
            return n;

        FibonacciProblemOptimization fib1 = new FibonacciProblemOptimization(n - 1);
        FibonacciProblemOptimization fib2 = new FibonacciProblemOptimization(n - 2);

//        fib1.fork();
        fib2.fork();

        // The actual thread execute the fib1
        // and we create another thread (insert it into the pool associated with fib2)
        return fib1.compute() + fib2.join();
    }
}
