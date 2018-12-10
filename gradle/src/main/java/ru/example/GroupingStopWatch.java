package ru.example;

import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

public class GroupingStopWatch {
    private final Map<String, Long> timers = new HashMap<>();
    private long lastStartTime;
    private String lastTask;
    private boolean isRunning;

    public GroupingStopWatch() {
    }

    public void start(String taskName) {
        if(isRunning) {
            stop();
        }
        isRunning = true;
        lastTask = taskName;
        lastStartTime = System.currentTimeMillis();
    }

    private void stop() {
        if(!isRunning) {
            throw new IllegalStateException("Trying to stop GroupingStopWatch that isn't running!");
        }
        timers.compute(lastTask, (s, time) -> increment(time));
        isRunning = false;

    }

    private Long increment(@Nullable Long originalTime) {
        if(originalTime == null) {
            return elapsedTime();
        } else {
            return originalTime + elapsedTime();
        }
    }

    private long elapsedTime() {
        long now = System.currentTimeMillis();
        return now - lastStartTime;
    }
}
