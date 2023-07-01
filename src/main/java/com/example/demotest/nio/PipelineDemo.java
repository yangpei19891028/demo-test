package com.example.demotest.nio;

public class PipelineDemo {
    HandlerChainContext head = new HandlerChainContext(new AbstractHandler() {
        @Override
        void handler(Object arg, HandlerChainContext handlerChainContext) {
            handlerChainContext.runNext(arg);
        }
    });

    void addLast(AbstractHandler handler){
        HandlerChainContext head = this.head;
        while(head.next!=null){
            head = head.next;
        }
        head.next = new HandlerChainContext(handler);
    }

    void start(Object arg){
        head.doHandler(arg);
    }

    public static void main(String[] args) {
        PipelineDemo pipelineChainDemo = new PipelineDemo();
        pipelineChainDemo.addLast(new Handler2());
        pipelineChainDemo.addLast(new Handler1());
        pipelineChainDemo.addLast(new Handler1());
        pipelineChainDemo.addLast(new Handler2());

        // 发起请求
        pipelineChainDemo.start("火车呜呜呜~~");

    }
}

class HandlerChainContext{
    HandlerChainContext next;
    AbstractHandler handler;

    public HandlerChainContext(AbstractHandler handler){
        this.handler = handler;
    }

    void doHandler(Object arg){
        handler.handler(arg,this);
    }

    void runNext(Object arg){
        if(this.next != null){
            next.doHandler(arg);
        }
    }
}

abstract class AbstractHandler{
    abstract void handler(Object arg,HandlerChainContext handlerChainContext);
}

// 处理器具体实现类
class Handler1 extends AbstractHandler {
    @Override
    void handler(Object arg0,HandlerChainContext handlerChainContext) {
        arg0 = arg0.toString() + "..handler1的小尾巴.....";
        System.out.println("我是Handler1的实例，我在处理：" + arg0);
        // 继续执行下一个
        handlerChainContext.runNext(arg0);
    }
}

// 处理器具体实现类
class Handler2 extends AbstractHandler {
    @Override
    void handler(Object arg0,HandlerChainContext handlerChainContext) {
        arg0 = arg0.toString() + "..handler2的小尾巴.....";
        System.out.println("我是Handler2的实例，我在处理：" + arg0);
        // 继续执行下一个
        handlerChainContext.runNext(arg0);
    }
}

