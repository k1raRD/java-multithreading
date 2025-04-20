package com.k1rard.virtualThreads;

import java.time.Duration;
import java.util.concurrent.Callable;

public class LongProcessFail implements Callable<String> {

    private int timeToSleep;
    private String result;
    private boolean fail;

    public LongProcessFail(int timeToSleep, String result, boolean fail) {
        this.timeToSleep = timeToSleep;
        this.result = result;
        this.fail = fail;
    }

    @Override
    public String call() throws Exception {
        System.out.println(STR."Starting thread \{result}");
        Thread.sleep(Duration.ofSeconds(this.timeToSleep));
        if(fail) {
            System.out.println(STR."Failure in child thread: \{result}");
            throw new RuntimeException("Error");
        }
        System.out.println(STR."Finished thread: \{result}");
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

    public boolean isFail() {
        return fail;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }
}
