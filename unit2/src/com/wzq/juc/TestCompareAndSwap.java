package com.wzq.juc;

/**
 * @description:
 * @author: WzqStart
 * @create: 2020/4/21  12:20
 */

public class TestCompareAndSwap {
    public static void main(String[] args) {
        //实例化CAS方法类
        final CompareAndSwap cas = new CompareAndSwap();
        //循环创建10个线程并发执行模拟CAS方法
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = cas.get();
                    boolean b = cas.compareAndSet(expectedValue, (int) (Math.random() * 101));
                    //成功打印true  失败打印false
                    System.out.println(b);
                }
            }).start();
        }
    }
}

//模拟CAS算法类
class CompareAndSwap {
    //我们要去操作的这个数
    private int value;

    //去获取内存中我们要操作的数的值，即value的值。
    public synchronized int get() {
        return value;
    }

    //拿期望值跟当前内存中的值作比较 如果期望值==内存中的值 则把新值赋给value 无论成功赋值与否 都返回oldValue
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = this.value;
        if (oldValue == expectedValue) {
            this.value = newValue;
        }
        return oldValue;
    }

    //代用compareAndSwap(int expectedValue, int newValue)方法执行成功则返回true  失败返回false
    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}

