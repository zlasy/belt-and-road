package com.example.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private Object stu;

    public DynamicProxy(Object stu) {
        this.stu = stu;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("了解该学生的学习情况");
        System.out.println("Method:"+method);
        method.invoke(stu, args);
        System.out.println("鼓励下该同学");
        return null;
    }
}
