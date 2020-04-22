package com.wzq.juc;


interface Foo {
    public void sayHello();
}

/**
 * @description:
 * @author: WzqStart
 * @create: 2020/4/21  16:57
 * <p>
 * 1 函数式编程
 * 拷贝小括号   写死右箭头 落地大括号
 */
public class LambdaExpressDemo02 {
    public static void main(String[] args) {
      Foo foo=()->{
          System.out.println("hello");
      };
      foo.sayHello();
    }
}
