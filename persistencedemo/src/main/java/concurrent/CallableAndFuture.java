package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture {
    public static void main(String[] args) {
        List<Future<Integer>> list = new ArrayList<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            final int taskID = i;
            Future<Integer> future = threadPool.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                    System.out.println(taskID + "|" + Thread.currentThread().getId());
                    return taskID;
                }
            });
            list.add(future);
        }
        try {
            for (int j = 0; j < 10; j++) {
                System.out.println(list.get(j).get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
