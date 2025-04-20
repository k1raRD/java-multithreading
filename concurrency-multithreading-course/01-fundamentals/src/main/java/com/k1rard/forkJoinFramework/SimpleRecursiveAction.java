package com.k1rard.forkJoinFramework;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

    private int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {

        // if the task is too large then we split it and execute in parallel
        if(simulatedWork > 100) {
            System.out.println("Parallel execution and split the task...");
            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork/2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork/2);

            invokeAll(action1, action2);
        } else {
            System.out.println("The is rather small, so sequential executions is fine...");
            System.out.println("The size of the task: " + simulatedWork);
        }
    }
}
