package com.example.designpattern;

import com.example.designpattern.observer.FirstListener;
import com.example.designpattern.observer.RunApplication;
import com.example.designpattern.observer.SecondListener;
import com.example.designpattern.observer.ThirdListener;
import com.example.designpattern.proxy.DynamicProxy;
import com.example.designpattern.proxy.Person;
import com.example.designpattern.proxy.Student;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@Component
@Order(1)
public class MainApplication implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {

        runDynamicProxy();
    }

    private void runObserver() {
        RunApplication application = new RunApplication();
        FirstListener first = new FirstListener(application);
        SecondListener second = new SecondListener(application);
        ThirdListener third = new ThirdListener(application);

        application.started();
    }

    private void runDynamicProxy(){
        Person realStu = new Student();

        InvocationHandler handler = new DynamicProxy(realStu);

        Person proxyStu = (Person) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                realStu.getClass().getInterfaces(), handler);
        System.out.println(proxyStu.getClass().getName());
        proxyStu.giveMoney("李林");
        proxyStu.money();
    }
}
