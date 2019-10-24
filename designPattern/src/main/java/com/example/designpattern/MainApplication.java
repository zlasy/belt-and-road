package com.example.designpattern;

import com.example.designpattern.observer.FirstListener;
import com.example.designpattern.observer.RunApplication;
import com.example.designpattern.observer.SecondListener;
import com.example.designpattern.observer.ThirdListener;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class MainApplication implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {

        runObserver();
    }

    private void runObserver() {
        RunApplication application = new RunApplication();
        FirstListener first = new FirstListener(application);
        SecondListener second = new SecondListener(application);
        ThirdListener third = new ThirdListener(application);

        application.started();
    }
}
