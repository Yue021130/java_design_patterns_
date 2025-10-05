package com.pattern.proxy;

import java.util.Set;

/**
 * 代理模式客户端入口。
 */
public class ProxyDemo {

    public static void main(String[] args) {
        // 1. 基础真实服务
        ProductService realService = new ProductServiceImpl();

        // 2. 添加缓存代理
        ProductService cachedService = new ProductCacheProxy(realService);

        // 3. 再添加权限代理（缓存代理被权限代理包装）
        ProductService secureService = new ProductAccessProxy(cachedService,
                Set.of("admin"));

        ProductServiceClient client = new ProductServiceClient(secureService);

        long start1 = System.currentTimeMillis();
        client.displayProduct("P-001");
        System.out.println("首次查询耗时: " + (System.currentTimeMillis() - start1) + "ms");

        long start2 = System.currentTimeMillis();
        client.displayProduct("P-001");
        System.out.println("二次查询耗时: " + (System.currentTimeMillis() - start2) + "ms");
    }
}
