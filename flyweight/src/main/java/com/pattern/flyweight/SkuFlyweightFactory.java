package com.pattern.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 享元工厂：负责创建和管理 SKU 元数据对象。
 *
 * <p>对于同一个 skuId，始终返回同一个 {@link SkuMetadata} 实例。</p>
 */
public class SkuFlyweightFactory {

    private static final SkuFlyweightFactory INSTANCE = new SkuFlyweightFactory();

    private final Map<String, SkuMetadata> pool = new ConcurrentHashMap<>();

    private SkuFlyweightFactory() {
    }

    public static SkuFlyweightFactory getInstance() {
        return INSTANCE;
    }

    /**
     * 获取 SKU 元数据。若不存在则创建并缓存。
     */
    public SkuMetadata getSkuMetadata(String skuId, String name,
                                      String category, String imageUrl) {
        return pool.computeIfAbsent(skuId, key ->
                new SkuMetadata(key, name, category, imageUrl));
    }

    public SkuMetadata getSkuMetadata(String skuId) {
        SkuMetadata metadata = pool.get(skuId);
        if (metadata == null) {
            throw new IllegalArgumentException("SKU 元数据不存在: " + skuId);
        }
        return metadata;
    }

    public int getPoolSize() {
        return pool.size();
    }
}
