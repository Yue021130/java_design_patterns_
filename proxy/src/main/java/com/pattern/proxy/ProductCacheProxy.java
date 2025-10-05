package com.pattern.proxy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存代理：为商品查询添加本地缓存。
 *
 * <p>第一次查询时从真实服务获取并缓存；后续查询直接返回缓存结果。</p>
 */
public class ProductCacheProxy implements ProductService {

    private final ProductService realService;
    private final Map<String, Product> cache = new ConcurrentHashMap<>();

    public ProductCacheProxy(ProductService realService) {
        this.realService = realService;
    }

    @Override
    public Product getProductById(String productId) {
        Product cached = cache.get(productId);
        if (cached != null) {
            System.out.println("[缓存代理] 命中缓存: " + productId);
            return cached;
        }

        Product product = realService.getProductById(productId);
        if (product != null) {
            cache.put(productId, product);
            System.out.println("[缓存代理] 写入缓存: " + productId);
        }
        return product;
    }

    @Override
    public void updateStock(String productId, int stock) {
        // 先更新真实数据
        realService.updateStock(productId, stock);
        // 再失效缓存
        cache.remove(productId);
        System.out.println("[缓存代理] 缓存失效: " + productId);
    }

    public void clearCache() {
        cache.clear();
    }

    public int getCacheSize() {
        return cache.size();
    }
}
