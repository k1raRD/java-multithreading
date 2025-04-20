package com.k1rard.virtualThreads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class StructuredConcurrencyFailure {
    public static void main(String[] args) {
        // We do not pool virtual threads: We create new ones for every tasks
        // and we dispose them after they finished
        try(var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            var process1 = new LongProcessFail(3, "result 1", true);
            var process2 = new LongProcessFail(7, "result 2", false);

            // We submit the task in parallel
            Subtask<String> res1 = scope.fork(process1);
            Subtask<String> res2 = scope.fork(process2);

            try {
                // because virtual threads is good to use!!!
                scope.join();
                // if is a failure in any of the child threads: other threads will be terminated
                scope.throwIfFailed();
                System.out.println(STR."\{res1.get()} - \{res2.get()}");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                System.out.println("Terminating all the threads");
                throw new RuntimeException(e);
            }

            // combine the results
            // get() will not block, because the join() waits for threads to finish

            // It will shut down the scope after all child threads terminate
        }
    }
}
