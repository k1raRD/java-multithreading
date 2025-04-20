package com.k1rard.virtualThreads;

public class RestQuery {
    public static String run() {
        System.out.println("Rest query started...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Rest Query finished...");
        return "Adam ";
    }
}
