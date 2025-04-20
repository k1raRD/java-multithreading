package com.k1rard.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketUpdated implements Runnable {
    @Override
    public void run() {
        System.out.println("Updating and downloading stock related data from the web...");
    }
}

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new StockMarketUpdated(), 1000, 2000, TimeUnit.MILLISECONDS);
    }
}
