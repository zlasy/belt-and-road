package concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ExecutorCompletion {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
        for(int i = 1; i < 10; i++) {
            final int taskID = i;
            cs.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
//                    System.out.println(taskID + "|" +Thread.currentThread().getId());
                    return taskID;
                }
            });
        }
        // 可能做一些事情
        for(int i = 1; i < 10; i++) {
            try {
                System.out.println(cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}