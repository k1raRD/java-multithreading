package com.k1rard.forkJoinFramework;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class PrintNumberAction extends RecursiveAction {

    private List<Integer> numbers;

    public PrintNumberAction(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    protected void compute() {
        // The problem is small enough (containing 2 items)
        if(numbers.size() < 2) {
            for (Integer num: numbers)
                System.out.println(num);
        } else {
            // Otherwise we split the problem into 2 sub-problems
            // [a, b, c] --> [a] and [b, c]
            // [a, b, c, d] --> [a, b] and [c, d]
            List<Integer> left = numbers.subList(0, numbers.size() / 2);
            List<Integer> right = numbers.subList(numbers.size() / 2, numbers.size());

            PrintNumberAction action1 = new PrintNumberAction(left);
            PrintNumberAction action2 = new PrintNumberAction(right);

            // it means these actions are thrown into the pool
            // fork-join assigns different threads to different tasks
            // so these will be executed with different threads
            invokeAll(action1, action2);
        }
    }
}
