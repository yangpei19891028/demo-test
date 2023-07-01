package com.example.demotest.thread;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * @Auther: zd
 * @Description:
 * @Date: 2022/12/13 1:10
 */
public class YellowCar {
    private static int[] initNum = {30, 40, 30};
    // 当前站点数量
    private static List<AtomicInteger> StationABC = new ArrayList();
    private static int peopleSpeed = 1;
    private static int vanSpeed = 3;
    // 货车状态 初始0 去装货1 运货2
    private static int[] vanStatus = {0, 0};
    // 货车步伐
    private static int[] vanStep = {0, 0};
    // 装货量
    private static int[] vanNums = {0, 0};
    // 货车位置(装货位置，卸货位置)
    private static int[] vanStation = {0, 0};
    // 需要货车的阈值
    //TODO 为了演示效果B站台的阈值只有41，C只有31，实际要大于
    private static int[] vanThreshold = {40, 41, 31};
    // 车站之间的详细距离
    private static int[][] ABCTime = {{14, 4}, {9, 14}, {4, 9}};
    // 车站之间的距离
    private static int[] ABCDis = {14, 4, 9};
    // 缓存行人路上时间
    private static List<Integer> peopleTimeArr = new LinkedList<>();
    // 路上的行人
    private static AtomicInteger roadNum = new AtomicInteger(0);
    // 运行时间
    private static volatile int time = 200;

    static Map<Integer, String> indexABC = new HashMap() {
        {
            put(0, "A");
            put(1, "B");
            put(2, "C");
        }
    };
    static Object timeLock = new Object();
    static Random random = new Random();

    static {
        StationABC.add(new AtomicInteger(30));
        StationABC.add(new AtomicInteger(40));
        StationABC.add(new AtomicInteger(30));
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("A:" + StationABC.get(0) + " " + "B:" + StationABC.get(1) + " " + "C:" + StationABC.get(2));
        // people van 的时间轮
        new Thread(() -> peopleStart()).start();
        // 监听用户到站
        new Thread(() -> peopleEnd()).start();
        // 货车出发
        new Thread(() -> vanRun()).start();
        // 货车到达装货点并装车
        new Thread(() -> vanLoader()).start();
        // 货车到达送货点并卸车
        new Thread(() -> vanUnLoader()).start();
        for (; ; ) {
            synchronized (timeLock) {
                StringBuffer log = new StringBuffer("A:" + StationABC.get(0) + " " + "B:" + StationABC.get(1) + " " + "C:" + StationABC.get(2) + " 路上:" + roadNum.get());
                for (int i = 0; i < vanStatus.length; i++) {
                    switch (vanStatus[i]){
                        case 1:log.append(" 货车"+(i+1)+"去"+indexABC.get(vanStation[i])+"装车");break;
                        case 2:log.append(" 货车"+(i+1)+"去"+indexABC.get(vanStation[i])+"卸车，装有:"+vanNums[i]);break;
                    }
                }
                System.out.println(log);
                timeLock.wait();
            }
        }

    }

    private static void vanUnLoader() {
        for (; ; ) {
            synchronized (timeLock) {
                try {
                    for (int i = 0; i < vanStatus.length; i++) {
                        if (vanStatus[i] == 2 && vanStep[i] == 0) {
                            // 卸货
                            StationABC.get(vanStation[i]).set(StationABC.get(vanStation[i]).get()+vanNums[i]);
                            vanNums[i] = 0;
                            vanStatus[i] = 0;
                        }
                    }
                    timeLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void vanLoader() {
        for (; ; ) {
            synchronized (timeLock) {
                try {
                    for (int i = 0; i < vanStatus.length; i++) {
                        if (vanStatus[i] == 1 && vanStep[i] == 0) {
                            // 装货量
                            vanNums[i] = StationABC.get(vanStation[i]).get() - initNum[vanStation[i]];
                            StationABC.get(vanStation[i]).set(initNum[vanStation[i]]);
                            // 选一个当前车最少的当目的地
                            // StationABC.stream().min(Comparator.comparing(AtomicInteger::get))
                            int tmp = 100;
                            int toStation = 0;
                            for (int j = 0; j < 3; j++) {
                                if (StationABC.get(j).get() < tmp) {
                                    toStation = j;
                                    tmp = StationABC.get(j).get();
                                }
                            }
                            int vanGOTOtime = vanStation[i] == toStation ? 0 : ABCDis[Integer.max((vanStation[i] + toStation) - 1, 0)] / vanSpeed + 1;
                            vanStatus[i] = 2;
                            vanStep[i] = vanGOTOtime;
                            vanStation[i] = toStation;
                        }
                    }
                    timeLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void vanRun() {
        for (; ; ) {
            synchronized (timeLock) {
                try {
                    // 默认一开始两车都在A 每次都装超过初始的个数
                    for (int i = 0; i < StationABC.size(); i++) {
                        int num = StationABC.get(i).get();
                        // 如果已经有车出发了就不去了
                        if (num >= vanThreshold[i] && !arrayContain(vanStation,i)) {
                            // 先看是否有空闲车辆
                            for (int j = 0; j < vanStatus.length; j++) {
                                if (vanStatus[j] == 0) {
                                    // 从当前位置向目标出发 逻辑：当前位置加上目标位置，得到距离 距离-1 得到实际距离的下标
                                    // 实际距离除以速度向上取整得到时间
                                    // 需要注意如果就在本车站则为0，不能取负数
                                    int vanGOTOtime = vanStation[j] == i ? 0 : ABCDis[Integer.max((vanStation[j] + i) - 1, 0)] / vanSpeed + 1;
                                    vanStatus[j] = 1;
                                    vanStep[j] = vanGOTOtime;
                                    // 货车位置变更
                                    vanStation[j] = i;
                                    // 有一辆货车出发就够了
                                    break;
                                }
                            }
                        }
                    }
                    timeLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private static void peopleStart() {
        while (time >= 0) {
            try {
                synchronized (timeLock) {
                    time--;
                    // 随机选择一个出发的车站
                    int startStation = random.nextInt(3);

                    // TODO 为了演示效果都在A站台出发，实际情况把下面这行注释
                    startStation = 0;

                    AtomicInteger station = StationABC.get(startStation);
                    station.decrementAndGet();
                    roadNum.incrementAndGet();
                    int[] startStationTime = ABCTime[startStation];
                    // 随机选择一个去的车站并计算距离 并计算花费的时间
                    int endStation = random.nextInt(2);
                    int peopleTime = startStationTime[endStation] * peopleSpeed;
                    // System.out.println("people开始用车，需要花费时间："+peopleTime);
                    peopleTimeArr.add(peopleTime);
                    // 行人步伐
                    peopleTimeArr = peopleTimeArr.stream().map(x -> x = Integer.max(x - 1, 0)).collect(Collectors.toList());
                    // 货车步伐
                    for (int i = 0; i < vanStep.length; i++) {
                        vanStep[i] = Integer.max(vanStep[i] - 1, 0);
                    }
                    timeLock.wait(1000);
                    timeLock.notifyAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void peopleEnd() {
        for (; ; ) {
            synchronized (timeLock) {
                // System.out.println(peopleTimeArr);
                Iterator<Integer> iterator = peopleTimeArr.iterator();
                while (iterator.hasNext()) {
                    Integer next = iterator.next();
                    if (next == 0) {
                        iterator.remove();
                        // 到站了
                        roadNum.decrementAndGet();
                        // 简化逻辑 随机选取一个目的地，正常逻辑需要记录起点和终点，因为有货车逻辑所以可以补偿，和真实结果一样
                        AtomicInteger station = StationABC.get(random.nextInt(3));
                        station.incrementAndGet();
                    }
                }
                try {
                    timeLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private static boolean arrayContain(int[] vanStation, int i) {
        for (int j = 0; j < vanStation.length; j++) {
            if (vanStation[j] == i)
                return true;
        }
        return false;
    }


}
