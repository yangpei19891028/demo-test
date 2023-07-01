package com.example.demotest.pattern;

/**
 * 模板方法
 */
public class TemplateMethodDemo {

    public static void main(String[] args) {
        DiscountCalculator1 discountCalculator1 = new DiscountCalculator1();
        DiscountCalculator2 discountCalculator2 = new DiscountCalculator2();
        DiscountCalculator3 discountCalculator3 = new DiscountCalculator3();
        discountCalculator1.calculator();
        discountCalculator2.calculator();
        discountCalculator3.calculator();
    }

    abstract static class AbsractDiscountCalculator{
        public void calculator(){
            //通用逻辑
            commonCalculator();
            //交给子类实现
            specificCalculator();
        }

        public void commonCalculator(){
            System.out.println("common");
        }

        abstract void specificCalculator();
    }

    static class DiscountCalculator1 extends AbsractDiscountCalculator{

        @Override
        void specificCalculator() {
            System.out.println("DiscountCalculator1");
        }
    }

    static class DiscountCalculator2 extends AbsractDiscountCalculator{

        @Override
        void specificCalculator() {
            System.out.println("DiscountCalculator2");
        }
    }

    static class DiscountCalculator3 extends AbsractDiscountCalculator{

        @Override
        void specificCalculator() {
            System.out.println("DiscountCalculator3");
        }
    }
}
