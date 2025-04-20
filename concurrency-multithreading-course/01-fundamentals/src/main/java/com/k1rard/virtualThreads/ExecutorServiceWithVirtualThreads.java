package com.k1rard.virtualThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceWithVirtualThreads {
    public static void main(String[] args) {

        // try with resource approach
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            executorService.submit(new VirtualTask());
            executorService.submit(new VirtualTask());
            executorService.submit(new VirtualTask());
        }

    }
}
