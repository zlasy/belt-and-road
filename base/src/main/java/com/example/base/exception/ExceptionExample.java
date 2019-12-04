package com.example.base.exception;

public class ExceptionExample {
    public static void main(String[] args) {

        try{
            method1();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void method1() {
        try{
            method2();
        } catch (Throwable e){
            throw new RuntimeException(e);
        }
    }

    private static void method2() {
        throw new NullPointerException();
    }
}
