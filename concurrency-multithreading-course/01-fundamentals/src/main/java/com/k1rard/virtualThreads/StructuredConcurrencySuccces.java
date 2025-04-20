package com.k1rard.virtualThreads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class StructuredConcurrencySuccces {
    public static void main(String[] args) {
        // We do not pool virtual threads: We create new ones for every tasks
        // and we dispose them after they finished
        try(var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {

            var process1 = new LongProcessFail(1, "result 1", true);
            var process2 = new LongProcessFail(10, "result 2", true);

            // We submit the task in parallel
            Subtask<String> res1 = scope.fork(process1);
            Subtask<String> res2 = scope.fork(process2);

            try {
                // because virtual threads is good to use!!!
                scope.join();
                String result = scope.result();
                System.out.println(result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                System.out.println("There is not solution...");
                throw new RuntimeException(e);
            }

            // combine the results
            // get() will not block, because the join() waits for threads to finish

            // It will shut down the scope after all child threads terminate
        }
    }
}
