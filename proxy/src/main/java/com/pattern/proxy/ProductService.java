package com.pattern.proxy;

/**
 * 主题接口：商品服务。
 */
public interface ProductService {

    Product getProductById(String productId);

    void updateStock(String productId, int stock);
}
