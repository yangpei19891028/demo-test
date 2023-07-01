package com.example.demotest.pattern;

public class AdapterDemo {

    public static void main(String[] args) {
        NewInterface newInterface = new NewInterfaceImpl();
        newInterface.b();;
        OldInterface oldInterface = new OldInterface() {
            @Override
            public void a() {
                System.out.println("old");
            }
        };
        NewInterface newInterfaceAdapter = new NewInterfaceImplAdapter(oldInterface);
        newInterfaceAdapter.b();
    }

    interface OldInterface{
        void a();
    }

    interface NewInterface{
        void b();
    }

    static class NewInterfaceImpl implements NewInterface{

        @Override
        public void b() {
            System.out.println("new");
        }
    }

    static class NewInterfaceImplAdapter implements NewInterface{

        OldInterface oldInterface;

        public NewInterfaceImplAdapter(OldInterface oldInterface){
            this.oldInterface = oldInterface;
        }

        @Override
        public void b() {
            oldInterface.a();
        }
    }
}
