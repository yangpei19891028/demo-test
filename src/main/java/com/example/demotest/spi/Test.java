package com.example.demotest.spi;

public class Test {

    public static void main(String[] args) {
        DefaultProcessChain defaultProcessChain = new DefaultProcessChain();
        Test1Slot slot1 = new Test1Slot();
        Test2Slot slot2 = new Test2Slot();
        defaultProcessChain.addLast(slot1);
        defaultProcessChain.addLast(slot2);
        defaultProcessChain.entry();
        defaultProcessChain.exit();
    }
}
