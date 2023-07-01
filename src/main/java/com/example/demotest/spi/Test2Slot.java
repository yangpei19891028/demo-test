package com.example.demotest.spi;

public class Test2Slot extends AbstractSlot {
    @Override
    public void entry() {
        System.out.println("entry2");
        super.fireEnrty();
    }

    @Override
    public void exit() {
        System.out.println("exit2");
        super.fireExit();
    }
}
