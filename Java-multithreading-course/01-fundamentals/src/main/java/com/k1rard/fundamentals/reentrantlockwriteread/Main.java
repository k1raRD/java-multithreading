package com.k1rard.fundamentals.reentrantlockwriteread;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    public static final int HIGHEST_PRICE = 1000;
    public static void main(String[] args) throws InterruptedException {
        InventoryDatabase inventoryDatabase = new InventoryDatabase();

        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
        }

        Thread writer = new Thread(() -> {
           while (true) {
               inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
               inventoryDatabase.removeItem(random.nextInt(HIGHEST_PRICE));

               try {
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });

        writer.setDaemon(true);
        writer.start();

        int numberOfReaderThreads = 7;
        List<Thread> readers = new ArrayList<>();

        for (int i = 0; i < numberOfReaderThreads; i++) {
            Thread reader = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    int upperBoundPrice = random.nextInt(HIGHEST_PRICE);
                    int lowerBoundPrice = upperBoundPrice > 0 ? random.nextInt(upperBoundPrice) : 0;
                    inventoryDatabase.getNumberOfItemsInPriceRange(lowerBoundPrice, upperBoundPrice);
                }
            });
            reader.setDaemon(true);
            readers.add(reader);
        }

        long startReadingTime = System.currentTimeMillis();

        for(Thread reader: readers) {
            reader.start();
        }

        for(Thread reader: readers) {
            reader.join();
        }

        long endReadingTime = System.currentTimeMillis();
        System.out.println(String.format("Reading took %d ms", endReadingTime - startReadingTime));
    }

    public static class InventoryDatabase {
        private TreeMap<Integer, Integer> priceCountToMap = new TreeMap<>();
        private ReentrantLock lock = new ReentrantLock();
        private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        private Lock readLock = reentrantReadWriteLock.readLock();
        private Lock writeLock = reentrantReadWriteLock.writeLock();

        public int getNumberOfItemsInPriceRange(int lowBound, int upperBound) {
            readLock.lock();
            try {
                Integer fromKey = priceCountToMap.ceilingKey(lowBound);
                Integer toKey = priceCountToMap.floorKey(upperBound);
                if(fromKey == null || toKey == null) {
                    return 0;
                }

                NavigableMap<Integer, Integer> rangeOfPrices = priceCountToMap.subMap(fromKey, true, toKey, true);

                int sum = 0;
                for(int numberOfItemsForPrice: rangeOfPrices.values()) {
                    sum += numberOfItemsForPrice;
                }

                return sum;
            } finally {
                readLock.unlock();
            }
        }

        public void addItem(int price) {
            writeLock.lock();
            try {
                Integer numberOfItemsPrice = priceCountToMap.get(price);
                if(numberOfItemsPrice == null) {
                    priceCountToMap.put(price, 1);
                } else {
                    priceCountToMap.put(price, numberOfItemsPrice + 1);
                }
            } finally {
                writeLock.unlock();
            }
        }

        public void removeItem(int price) {
            writeLock.lock();
            try {
                Integer numberOfItemsForPrice = priceCountToMap.get(price);
                if(numberOfItemsForPrice == null || numberOfItemsForPrice == 1) {
                    priceCountToMap.remove(price);
                } else {
                    priceCountToMap.put(price, numberOfItemsForPrice - 1);
                }
            } finally {
                writeLock.unlock();
            }
        }
    }
}
