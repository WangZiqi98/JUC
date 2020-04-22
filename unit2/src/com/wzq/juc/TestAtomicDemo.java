package com.wzq.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: i++原子性的问题
 * @author: WzqStart
 * @create: 2020/4/21  9:27
 * <p>
 *
 * 一：i++原子性问题：i++实际上分为三个步骤的 “读”--“改”--“写”
 * int i=10；
 * i=i++//10
 * <p>
 * int temp=i;
 * i=i+1;
 * i=temp;
 *
 * 二原子变量：jdk1.5之后 java.util.concurrent包下提供了常用的原子变量
 *         1.volatile 内存可见性
 *         2.CAS保证数据的原子性
 */
public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i <10 ; i++) {
            new Thread(ad).start();
        }
    }

}

class AtomicDemo implements Runnable {
    //把serialNumber生命成AtomicInteger类型
    private AtomicInteger serialNumber = new AtomicInteger(0);
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(getSerialNumber());
    }
    //get方法
    public int getSerialNumber() {
        //这里就不能用原来的++方法，而是替换成AtomicInteger自带的getAndIncrement()方法实现读取并自增1
        return serialNumber.getAndIncrement();
    }
}
