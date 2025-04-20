package com.k1rard.threadSafeCollection.delayQueue;

import java.util.concurrent.*;

class DelayWorker implements Delayed {

    private long duration;
    private String message;

    public DelayWorker(long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    // This is the method than compare objects
    // -1, 1, or 0
    @Override
    public int compareTo(Delayed other) {
        if(duration < ((DelayWorker) other).getDuration())
            return -1;

        if(duration > ((DelayWorker) other).getDuration())
            return 1;

        return 0;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DelayWorker{" +
                "message='" + message + '\'' +
                '}';
    }
}

public class DelayQueueExample {

    public static void main(String[] args) {
        BlockingQueue<DelayWorker> queue = new DelayQueue<>();

        try {
            // these can be inserted by different threads
            queue.put(new DelayWorker( 2000, "This is the first message..."));
            queue.put(new DelayWorker( 10000, "This is the second message..."));
            queue.put(new DelayWorker( 4500, "This is the third message..."));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // We can get the messages
        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
