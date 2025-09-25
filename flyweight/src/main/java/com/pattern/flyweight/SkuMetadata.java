package com.pattern.flyweight;

/**
 * 享元对象：SKU 元数据（内部状态）。
 *
 * <p>SKU 的基本信息（名称、类目、图片）在大量订单项中是重复的，
 * 通过共享同一份元数据对象，可以显著减少内存占用。</p>
 *
 * <p>使用 Java Record 保证不可变，适合被多线程安全共享。</p>
 */
public record SkuMetadata(
        String skuId,
        String name,
        String category,
        String imageUrl
) {
}
