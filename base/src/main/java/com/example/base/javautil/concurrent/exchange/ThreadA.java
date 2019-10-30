package com.example.base.javautil.concurrent.exchange;

import java.util.concurrent.Exchanger;

public class ThreadA implements Runnable{

    private Exchanger<String> exchanger;

    public ThreadA(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println(exchanger.exchange("data from Thread A"));
            System.out.println("Thread A end...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
