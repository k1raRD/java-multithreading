package com.k1rard.threadSafeCollection.copyOnWrireArray;

import java.util.List;

public class CopyOnWriteArrayExample {
    public static void main(String[] args) {
        ConcurrentArray concurrentArray = new ConcurrentArray();
        concurrentArray.simulate();
    }
}
