package com.k1rard.threadSafeCollection.priorityQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class Person implements Comparable<Person> {

    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person person) {
        return name.compareTo(person.getName());
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

class FirstWorker implements Runnable {

    private BlockingQueue<Person> queue;

    public FirstWorker(BlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put(new Person(12, "Adam"));
            queue.put(new Person(55, "Kevin"));
            queue.put(new Person(27, "Ana"));
            Thread.sleep(2000);
            queue.put(new Person(31, "Daniel"));
            Thread.sleep(1000);
            queue.put(new Person(15, "Joe"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class SecondWorker implements Runnable {

    private BlockingQueue<Person> queue;

    public SecondWorker(BlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(queue.take());
            Thread.sleep(1000);
            System.out.println(queue.take());
            Thread.sleep(2000);
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class PriorityQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();

        FirstWorker firstWorker = new FirstWorker(queue);
        SecondWorker secondWorker = new SecondWorker(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }
}
