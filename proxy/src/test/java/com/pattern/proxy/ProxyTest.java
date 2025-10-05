package com.pattern.proxy;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProxyTest {

    @Test
    void cacheProxyShouldReduceQueryTime() {
        ProductService realService = new ProductServiceImpl();
        ProductCacheProxy proxy = new ProductCacheProxy(realService);

        long start1 = System.currentTimeMillis();
        Product first = proxy.getProductById("P-001");
        long duration1 = System.currentTimeMillis() - start1;

        long start2 = System.currentTimeMillis();
        Product second = proxy.getProductById("P-001");
        long duration2 = System.currentTimeMillis() - start2;

        assertSame(first, second);
        assertTrue(duration1 >= 100, "首次查询应访问真实服务，耗时 >= 100ms");
        assertTrue(duration2 < 50, "二次查询应命中缓存，耗时 < 50ms");
    }

    @Test
    void cacheProxyShouldEvictOnStockUpdate() {
        ProductService realService = new ProductServiceImpl();
        ProductCacheProxy proxy = new ProductCacheProxy(realService);

        Product first = proxy.getProductById("P-001");
        assertEquals(100, first.stock());

        // 直接修改真实服务库存，缓存应失效
        proxy.updateStock("P-001", 80);
        assertEquals(0, proxy.getCacheSize());

        Product updated = proxy.getProductById("P-001");
        assertEquals(80, updated.stock());
    }

    @Test
    void accessProxyShouldAllowQueryButDenyUpdateForNormalUser() {
        ProductService realService = new ProductServiceImpl();
        ProductAccessProxy proxy = new ProductAccessProxy(realService,
                Set.of("admin"));

        // 查询允许
        Product product = proxy.getProductById("P-001");
        assertNotNull(product);

        // 当前线程名为非 admin，应拒绝
        Thread.currentThread().setName("normal-user");
        assertThrows(SecurityException.class, () -> proxy.updateStock("P-001", 50));
    }

    @Test
    void accessProxyShouldAllowAdminUpdate() {
        ProductService realService = new ProductServiceImpl();
        ProductAccessProxy proxy = new ProductAccessProxy(realService,
                Set.of("admin"));

        Thread.currentThread().setName("admin");
        assertDoesNotThrow(() -> proxy.updateStock("P-001", 50));

        Product updated = proxy.getProductById("P-001");
        assertEquals(50, updated.stock());
    }
}
