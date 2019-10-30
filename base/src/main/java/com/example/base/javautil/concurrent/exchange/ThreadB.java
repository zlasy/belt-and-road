package com.example.base.javautil.concurrent.exchange;

import java.util.concurrent.Exchanger;

public class ThreadB implements Runnable{

    private Exchanger<String> exchanger;

    public ThreadB(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println(exchanger.exchange("data from Thread B"));
            System.out.println("Thread B end...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
