package com.example.demotest.spi;

public class DefaultProcessChain {
    AbstractSlot first = new AbstractSlot() {
        @Override
        public void entry() {
            super.fireEnrty();
        }

        @Override
        public void exit() {
            super.fireExit();
        }
    };

    AbstractSlot end = first;

    public void addFirst(AbstractSlot slot){
        slot.setNext(first.getNext());
        first.setNext(slot);
        if(end == first){
            end = slot;
        }
    }

    public void addLast(AbstractSlot slot){
        end.setNext(slot);
        end = slot;
    }

    public void entry(){
        first.entry();
    }

    public void exit(){
        first.exit();
    }
}
