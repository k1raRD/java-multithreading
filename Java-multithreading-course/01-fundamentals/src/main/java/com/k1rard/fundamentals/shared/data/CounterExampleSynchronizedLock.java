package com.k1rard.fundamentals.shared.data;

public class CounterExampleSynchronizedLock {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();

        decrementingThread.join();
        incrementingThread.join();
        System.out.println("We currently have: " + inventoryCounter.getItems() + " items");
    }

    public static class DecrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    public static class IncrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
            System.out.println(inventoryCounter.items);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
                System.out.println(inventoryCounter.items);
            }
        }
    }

    private static class InventoryCounter {
        private int items = 0;
        final Object lock = new Object();

        public void increment() {
            synchronized (this.lock) {
                this.items++;
            }
        }

        public void decrement() {
            synchronized (this.lock) {
                this.items--;
            }
        }
        public int getItems() {
            synchronized (this.lock) {
                return this.items;
            }
        }
    }
}
