package com.example.demotest.singleton;

public enum SingletonEnum {
    INSTANCE;
    private Obj o = null;
    SingletonEnum(){
        o = new Obj();
    }

    public Obj getO(){
        return o;
    }
    class Obj {

    }

    public static void main(String[] args) {
        Obj o1 = SingletonEnum.INSTANCE.getO();
        Obj o2 = SingletonEnum.INSTANCE.getO();
        System.out.println(o1 == o2);

        int newCapacity = 35;
        newCapacity |= newCapacity >>> 1;
        System.out.println(newCapacity);
        newCapacity |= newCapacity >>> 2;
        System.out.println(newCapacity);
        newCapacity |= newCapacity >>> 4;
        System.out.println(newCapacity);
        newCapacity |= newCapacity >>> 8;
        System.out.println(newCapacity);
        newCapacity |= newCapacity >>> 16;
        newCapacity++;
        System.out.println(newCapacity);
    }
}
