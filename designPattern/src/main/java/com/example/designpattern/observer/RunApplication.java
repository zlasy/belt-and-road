package com.example.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class RunApplication {

    private List<RunListener> listeners = new ArrayList<>();

    void regist(RunListener listener){
        this.listeners.add(listener);
    }

    public void started(){
        for (RunListener listener: listeners) {
            listener.started();
        }
    }
}
