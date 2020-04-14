package com.syb.concurrent.singleton_demo;

public class InnerClassSingletonDemo {

    private InnerClassSingletonDemo(){

    }
    private static class Holder{
        private static InnerClassSingletonDemo instance=new InnerClassSingletonDemo();
    }

    //懒加载
    //synchronized
    //<init>
    public static InnerClassSingletonDemo getInstance(){
        return Holder.instance;
    }

    //广泛的一种单例模式
}




