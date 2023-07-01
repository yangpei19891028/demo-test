package com.example.demotest.test;

public class Test1 {

    public static void main(String[] args) {
        //t();
        //System.out.println(s());
        //r();
        //q();
        u();
    }

    public static void t(){
        try{
            try{
                System.out.println("1_1");//对应@Around注解的方法切面逻辑(前置)
                System.out.println("2");//对应@Before注解的方法切面逻辑
                System.out.println(1/0);//目标方法
                System.out.println("1_2");//对应@Around注解的方法切面逻辑(后置)
            }finally{
                System.out.println(3);;//对应@After注解的方法切面逻辑
            }
            System.out.println(4);//对应@AfterReturning注解的方法切面逻辑
        }catch(Exception e){
            System.out.println(5);//对应@AfterThrowing注解的方法切面逻辑
        }

    }

    public static int s(){
        try{
            //System.out.println(1);
            //System.out.println(1/0);
            return 1;
        }catch (Exception e){

        }finally {
            //System.out.println(2);
            //return 2;
        }
        return 0;
    }

    public static int r(){
        try {
            try {
                System.out.println(1 / 0);
            } finally {
                System.out.println(1111);
            }
        }catch (Exception e){
            System.out.println(22222);
        }
        return 0;
    }

    public static void q(){
        for (int binCount = 0; ; ++binCount) {
            System.out.println(binCount);
            if(binCount == 4){
                break;
            }
        }
    }

    public static void u(){
        while(true){
            try{
                System.out.println(1);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(2);
            }
        }
    }
}
