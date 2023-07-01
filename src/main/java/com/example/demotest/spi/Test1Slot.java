package com.example.demotest.spi;

public class Test1Slot extends AbstractSlot {
    @Override
    public void entry() {
        System.out.println("entry1");
        super.fireEnrty();
    }

    @Override
    public void exit() {
        System.out.println("exit1");
        super.fireExit();
    }
}
