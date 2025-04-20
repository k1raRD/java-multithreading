package com.k1rard.threadSafeCollection.concurrentMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class MapFirstWorker implements Runnable {

    private ConcurrentMap<String, Integer> map;

    public MapFirstWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            map.put("B", 12);
            Thread.sleep(1000);
            map.put("Z", 5);
            map.put("A", 20);
            Thread.sleep(2000);
            map.put("D", 19);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class MapSeondWorker implements Runnable {

    private ConcurrentMap<String, Integer> map;

    public MapSeondWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(map.get("A"));;
            Thread.sleep(2000);
            System.out.println(map.get("Z"));
            System.out.println(map.get("B"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class ConcurrentMapExample {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        MapFirstWorker firstWorker = new MapFirstWorker(map);
        MapSeondWorker seondWorker = new MapSeondWorker(map);

        new Thread(firstWorker).start();
        new Thread(seondWorker).start();
    }
}
