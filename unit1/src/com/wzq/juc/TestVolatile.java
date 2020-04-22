package com.wzq.juc;

/**
 * @description: volatile关键字:当多个线程进行操作共享数据时，可以保证内存中的数据可见性
 * @author: WzqStart
 * @create: 2020/4/21  8:49
 * <p>
 * 内存可见性问题是，当多个线程操作共享数据时，彼此不可见
 * Volatile相较于synchronized 是一种轻量级的同步策略
 * 但是 volatile不具备互斥性 volatile不能保证变量的原子性
 */
public class TestVolatile {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true) {
            if (td.isFlag()) {
                System.out.println("----------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {
    private volatile boolean flag = false;
    //get方法
    public boolean isFlag() {
        return flag;
    }
    //set方法
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag=" + isFlag());
    }

}
