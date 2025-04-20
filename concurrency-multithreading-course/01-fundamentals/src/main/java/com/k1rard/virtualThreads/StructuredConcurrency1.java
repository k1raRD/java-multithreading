package com.k1rard.virtualThreads;

import java.util.concurrent.*;
import java.util.concurrent.StructuredTaskScope.*;

public class StructuredConcurrency1 {
    public static void main(String[] args) {
        // We do not pool virtual threads: We create new ones for every tasks
        // and we dispose them after they finished
        try(var scope = new StructuredTaskScope<String>()) {

            var process1 = new LongProcess(3, "result 1");
            var process2 = new LongProcess(7, "result 2");

            // We submit the task in parallel
            Subtask<String> res1 = scope.fork(process1);
            Subtask<String> res2 = scope.fork(process2);

            try {
                // because virtual threads is good to use!!!
                scope.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(res1.state() == Subtask.State.SUCCESS) {
                System.out.println(res1.get());
            }

            if(res2.state() == Subtask.State.SUCCESS) {
                System.out.println(res2.get());
            }

            // combine the results
            // get() will not block, because the join() waits for threads to finish

            // It will shut down the scope after all child threads terminate
        }
    }
}
