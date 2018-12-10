package ru.example;

import org.junit.Test;
import org.springframework.util.StopWatch;

public class StopWatchTest {
    @Test
    public void test() throws InterruptedException {

        StopWatch watch = new StopWatch("mainId");
        watch.setKeepTaskList(true);

        for (int i = 1; i < 10; i++) {
            watch.start("task1");
            Thread.sleep(100);
            watch.stop();
            watch.start("task2");
            Thread.sleep(300);
            watch.stop();
        }
        System.out.println(watch.prettyPrint());
    }
}
