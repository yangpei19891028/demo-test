package com.example.demotest.thread;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产奶酪
 *
 * @author since
 * @date 2020-11-02 16:04
 **/
public class Cheese {

    /**
     * 每天生产奶酪的总量
     */
    private static final Integer TOTAL_PRODUCTION = 100000;

    /**
     * 牛奶的数量-200000/天
     */
    private static final AtomicInteger MILK_SIZE = new AtomicInteger(0);

    /**
     * 发酵剂的数量-100000/天
     */
    private static final AtomicInteger STARTER_SIZE = new AtomicInteger(0);

    /**
     * 奶酪的数量-100000/天
     */
    private static final AtomicInteger CHEESE_SIZE = new AtomicInteger(0);

    /**
     * 取奶酪的次数
     */
    private static final AtomicInteger CHEESE_TAKE_TIMES = new AtomicInteger(0);


    /**
     * 阻塞队列，用于存放奶酪，生产者是牛奶和发酵剂，消费者是货车发货，每次发100份
     */
    private static final BlockingQueue<Integer> CHEESE_QUEUE = new LinkedBlockingQueue<>(1000);

    /**
     * 阻塞队列，用于存放生产的牛奶，生产者是牛奶，消费者是奶酪生产
     */
    private static final BlockingQueue<Integer> MILK_QUEUE = new LinkedBlockingQueue<>(2);

    /**
     * 阻塞队列，用于存放生产的发酵剂，生产者是发酵剂，消费者是奶酪生产
     */
    private static final BlockingQueue<Integer> STARTER_QUEUE = new LinkedBlockingQueue<>(1);

    /**
     * 奶酪生产线程池
     */
    private static final ThreadPoolExecutor CHEESE_PRODUCER = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1), new ThreadFactoryBuilder().setNameFormat("cheese-producer-%d").build(), (r, executor) -> System.out.println("Cheese Reject Task"));

    /**
     * 发酵剂生产线程池
     */
    private static final ThreadPoolExecutor STARTER_PRODUCER = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1), new ThreadFactoryBuilder().setNameFormat("starter-producer-%d").build(), (r, executor) -> System.out.println("Starter Reject Task"));

    /**
     * 牛奶生产线程池
     */
    private static final ThreadPoolExecutor MILK_PRODUCER = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1), new ThreadFactoryBuilder().setNameFormat("milk-producer-%d").build(), (r, executor) -> System.out.println("Milk Reject Task"));

    /**
     * 货车发货线程池
     */
    private static final ThreadPoolExecutor TRUCKING = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1), new ThreadFactoryBuilder().setNameFormat("trucking-%d").build(), (r, executor) -> System.out.println("Trunking Reject Task"));


    /**
     * 奶酪生产线程
     */
    static class CheeseProducer implements Runnable {

        @Override
        public void run() {
            while (CHEESE_SIZE.get() < TOTAL_PRODUCTION) {
                try {
                    //发酵剂取1
                    STARTER_QUEUE.take();
                    //System.out.println("生产奶酪，发酵剂取1");

                    //牛奶取2
                    MILK_QUEUE.take();
                    MILK_QUEUE.take();

                    //System.out.println("生产奶酪，发酵剂取2");

                    //奶酪生产1
                    CHEESE_QUEUE.put(1);
                    //System.out.println("生产1份奶酪");

                    //生产数量加1
                    int i = CHEESE_SIZE.incrementAndGet();

                    if (CHEESE_SIZE.get() == TOTAL_PRODUCTION) {
                        System.out.println("本日奶酪已生产完，共" + i + "份");
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 发酵剂生产线程
     */
    static class StarterProducer implements Runnable {

        @Override
        public void run() {
            while (STARTER_SIZE.get() < TOTAL_PRODUCTION) {
                try {
                    STARTER_QUEUE.put(1);
                    //System.out.println("生产1份发酵剂");
                    //发酵剂+1
                    int i = STARTER_SIZE.incrementAndGet();
                    if (STARTER_SIZE.get() == TOTAL_PRODUCTION) {
                        System.out.println("本日发酵剂已生产完，共" + i + "份");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    /**
     * 牛奶生产线程
     */
    static class MilkProducer implements Runnable {

        @Override
        public void run() {
            while (MILK_SIZE.get() < TOTAL_PRODUCTION * 2) {
                try {
                    MILK_QUEUE.put(1);
                    //System.out.println("生产1份牛奶");

                    //牛奶数量+1
                    int i = MILK_SIZE.incrementAndGet();
                    if (MILK_SIZE.get() == TOTAL_PRODUCTION * 2) {
                        System.out.println("本日牛奶已生产完，共" + i + "份");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 货车发货线程
     */
    static class Trucking implements Runnable {
        @Override
        public void run() {

            while (true) {
                try {
                    //取奶酪装车准备发货
                    CHEESE_QUEUE.take();

                    int i1 = CHEESE_TAKE_TIMES.incrementAndGet();

                    if (i1 % 100 == 0) {
                        System.out.println("货车装满100份，发车");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public static void main(String[] args) {
        CHEESE_PRODUCER.execute(new CheeseProducer());

        STARTER_PRODUCER.execute(new StarterProducer());

        MILK_PRODUCER.execute(new MilkProducer());

        TRUCKING.execute(new Trucking());

        while (true) {
            if (CHEESE_SIZE.get() == TOTAL_PRODUCTION) {
                MILK_PRODUCER.shutdown();
                STARTER_PRODUCER.shutdown();
                CHEESE_PRODUCER.shutdown();
                TRUCKING.shutdown();
                System.exit(0);
            }

        }
    }

}