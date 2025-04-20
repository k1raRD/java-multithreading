package com.k1rard.forkJoinFramework;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleRecursiveTask extends RecursiveTask<Integer> {

    private AtomicInteger number;

    public SimpleRecursiveTask(int num) {
        this.number = new AtomicInteger(num);
    }

    @Override
    protected Integer compute() {
        if(this.number.get() > 200 && (this.number.get() / 2 == 0)) {
            // parallelization: We split the problem into 2 sub-problems
            System.out.println("Parallel execution so split the task..." + number);

            SimpleRecursiveTask task1 = new SimpleRecursiveTask(this.number.get() / 2);
            SimpleRecursiveTask task2 = new SimpleRecursiveTask(this.number.get() / 2);

            // We add the tasks to the thread pool (parallel)
            task1.fork();
            task2.fork();

            // wait for these tasks to be finished
            AtomicInteger subSolution = new AtomicInteger(0);
            subSolution.addAndGet(task1.join());
            subSolution.addAndGet(task2.join());

            return subSolution.get();
        } else {
            // The problem is too small - we can use sequential approach
            System.out.println("The task is small... we can execute it sequentially: " + number);
            return this.number.get() * 2;
        }
    }
}
