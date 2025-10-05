package com.pattern.proxy;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 真实主题：实际查询商品信息的业务服务。
 *
 * <p>模拟从数据库查询，带有 100ms 延迟。</p>
 */
public class ProductServiceImpl implements ProductService {

    private final Map<String, Product> database = new ConcurrentHashMap<>();

    public ProductServiceImpl() {
        // 模拟数据库初始化
        database.put("P-001", new Product("P-001", "iPhone 15",
                "Apple iPhone 15 128GB", new BigDecimal("5999"), 100));
        database.put("P-002", new Product("P-002", "MacBook Pro",
                "Apple MacBook Pro 14", new BigDecimal("14999"), 50));
    }

    @Override
    public Product getProductById(String productId) {
        // 模拟数据库查询耗时
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return database.get(productId);
    }

    @Override
    public void updateStock(String productId, int stock) {
        Product product = database.get(productId);
        if (product != null) {
            database.put(productId, new Product(
                    product.productId(), product.name(), product.description(),
                    product.price(), stock));
        }
    }
}
