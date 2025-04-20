package com.k1rard.fundamentals.deadlock;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Interseccion interseccion = new Interseccion();
        Thread trainAThread = new Thread(new TrainA(interseccion));
        Thread trainBThread = new Thread(new TrainB(interseccion));

        trainAThread.start();
        trainBThread.start();
    }

    public static class TrainA implements Runnable {
        private Interseccion interseccion;
        private Random random = new Random();

        public TrainA(Interseccion interseccion) {
            this.interseccion = interseccion;
        }

        @Override
        public void run() {
            while (true) {
                long sleepingTIme = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTIme);
                } catch (InterruptedException e) {

                }

                interseccion.takeRoadA();
            }
        }
    }

    public static class TrainB implements Runnable {
        private Interseccion interseccion;
        private Random random = new Random();

        public TrainB(Interseccion interseccion) {
            this.interseccion = interseccion;
        }

        @Override
        public void run() {
            while (true) {
                long sleepingTIme = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTIme);
                } catch (InterruptedException e) {

                }

                interseccion.takeRoadB();
            }
        }
    }

    public static class Interseccion {
        private Object roadA = new Object();
        private Object roadB = new Object();

        public void takeRoadA() {
            synchronized (roadA) {
                System.out.println("Road A is locked by thread: " + Thread.currentThread().getName());

                synchronized (roadB) {
                    System.out.println("Train is passing through road A");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }

        public void takeRoadB() {
            synchronized (roadA) {
                System.out.println("Road B is locked by thread: " + Thread.currentThread().getName());
                synchronized (roadB) {
                    System.out.println("Train is passing through road B");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
