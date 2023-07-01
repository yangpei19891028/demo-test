package com.example.demotest.test;

public class Test10 {
    public static void main(String[] args) {
        /**
         * 服务 节点 批次 批次内序号
         *
         * 12bit 4bit 24bit 24bit
         */
        long service = 1;
        long node = 1;
        long version = 1;
        long sequence = 16777214;

        long service1 = 1;
        long node1 = 1;
        long version1 = 1;
        long sequence1 = 16777215;

        long service2 = 1;
        long node2 = 1;
        long version2 = 2;
        long sequence2 = 0;

        long service3 = 1;
        long node3 = 1;
        long version3 = 2;
        long sequence3= 1;
        System.out.println((service & 0xFFF) << 52 | (node & 0xF) << 48 | (version & 0xFFFFFF) << 24 | (sequence & 0xFFFFFF));
        System.out.println((service1 & 0xFFF) << 52 | (node1 & 0xF) << 48 | (version1 & 0xFFFFFF) << 24 | (sequence1 & 0xFFFFFF));
        System.out.println((service2 & 0xFFF) << 52 | (node2 & 0xF) << 48 | (version2 & 0xFFFFFF) << 24 | (sequence2 & 0xFFFFFF));
        System.out.println((service3 & 0xFFF) << 52 | (node3 & 0xF) << 48 | (version3 & 0xFFFFFF) << 24 | (sequence3 & 0xFFFFFF));
    }
}
