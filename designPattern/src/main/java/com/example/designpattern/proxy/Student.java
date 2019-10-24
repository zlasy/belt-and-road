package com.example.designpattern.proxy;

public class Student implements Person {

    @Override
    public void giveMoney(String name) {
        System.out.println(name + "同学已经班费100");
    }

    @Override
    public void money() {
        System.out.println(".........");
    }
}
