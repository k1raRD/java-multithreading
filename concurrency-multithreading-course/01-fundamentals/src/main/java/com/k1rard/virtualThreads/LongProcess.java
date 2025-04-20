package com.k1rard.virtualThreads;

import java.time.Duration;
import java.util.concurrent.Callable;

public class LongProcess implements Callable<String> {

    private int timeToSleep;
    private String result;

    public LongProcess(int timeToSleep, String result) {
        this.timeToSleep = timeToSleep;
        this.result = result;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(Duration.ofSeconds(this.timeToSleep));
        return result;
    }

    public int getTimeToSleep() {
        return timeToSleep;
    }

    public void setTimeToSleep(int timeToSleep) {
        this.timeToSleep = timeToSleep;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
