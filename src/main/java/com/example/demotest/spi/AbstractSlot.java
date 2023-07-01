package com.example.demotest.spi;

public abstract class AbstractSlot implements InterfaceSlot{

    private AbstractSlot next;

    public void fireEnrty(){
        if(next != null){
            next.entry();
        }
    }

    public void fireExit(){
        if(next != null){
            next.exit();
        }
    }

    public AbstractSlot getNext() {
        return next;
    }

    public void setNext(AbstractSlot next) {
        this.next = next;
    }
}
