package com.example.demotest.test;

import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class Test6 {
    public static void main(String[] args) throws ParseException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
//        executorService.scheduleAtFixedRate(new Runnable() {
//            private int count = 0;
//            private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//            @Override
//            public void run() {
//                String time = simpleDateFormat.format(new Date());
//                System.out.println(time + " >>第"+ (++count) + "次执行定时任务.");
//            }
//        }, 0, 2000, TimeUnit.MILLISECONDS);

//        executorService.scheduleWithFixedDelay(new Runnable() {
//            private int count = 0;
//            private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//            @SneakyThrows
//            @Override
//            public void run() {
//                String time = simpleDateFormat.format(new Date());
//                System.out.println(time + " >>第"+ (++count) + "次执行定时任务.");
//                Thread.sleep(3000L);
//            }
//        }, 0, 2000, TimeUnit.MILLISECONDS);

//        executorService.schedule(new Runnable() {
//            private int count = 0;
//            private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//
//            @Override
//            public void run() {
//                String time = simpleDateFormat.format(new Date());
//                System.out.println(time + " >>第"+ (++count) + "次执行定时任务.");
//                Thread.sleep(3000L);
//            }
//        }, 2000, TimeUnit.MILLISECONDS);

        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        ScheduledFuture<Integer> future = threadPoolExecutor.schedule(()->{
            System.out.println(1);
            return 1;
        },1,TimeUnit.SECONDS);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ScheduledFuture<Integer> future1 = threadPoolExecutor.schedule(()->{
            System.out.println(2);
            return 2;
        },1,TimeUnit.SECONDS);
        try {
            System.out.println(future1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
