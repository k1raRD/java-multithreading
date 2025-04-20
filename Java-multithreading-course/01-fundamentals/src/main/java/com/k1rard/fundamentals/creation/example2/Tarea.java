package com.k1rard.fundamentals.creation.example2;

public class Tarea {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            System.out.println("I am going for a walk");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("I am going to swim");
        });

        thread1.start();
        thread2.start();
        System.out.println("I am going home");
    }
}
