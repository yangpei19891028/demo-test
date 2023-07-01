package com.example.demotest.test;

public class Test2 {

    public static void main(String[] args) {
        Test2_2.Test2_1 test2_1 = new Test2_2.Test2_1();
        test2_1.a();
        Test2_2.Test2_1.b();
    }
}

class Test2_2{
    static Test2_1 test2_1;

    private static int a = 1;
    int c = 2;

    public Test2_2(){
        test2_1 = new Test2_1();
        System.out.println(Test2_1.b);
    }

    public static class Test2_1{
        Test2 test2;
        private static int b = 1;

        public void a(){
            System.out.println(a);
            System.out.println(111);
        }
        public static void b(){
            System.out.println(test2_1);
        }
    }
}
