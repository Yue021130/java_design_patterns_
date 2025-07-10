package com.pattern.singleton;

import java.util.stream.IntStream;

/**
 * 单例模式客户端入口：模拟订单服务批量生成订单号。
 */
public class SingletonDemo {

    public static void main(String[] args) {
        var generator = SnowflakeIdGenerator.getInstance(1L, 1L);

        System.out.println("批量生成 10 个订单号：");
        IntStream.rangeClosed(1, 10)
                .mapToObj(i -> "订单 " + i + ": " + generator.nextId())
                .forEach(System.out::println);

        // 验证多次获取的是同一个实例
        var another = SnowflakeIdGenerator.getInstance(1L, 1L);
        System.out.println("是否为同一实例: " + (generator == another));
    }
}
