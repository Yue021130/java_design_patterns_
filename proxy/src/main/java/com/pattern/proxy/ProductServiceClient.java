package com.pattern.proxy;

/**
 * 商品服务客户端：通过代理访问真实服务。
 */
public class ProductServiceClient {

    private final ProductService productService;

    public ProductServiceClient(ProductService productService) {
        this.productService = productService;
    }

    public void displayProduct(String productId) {
        Product product = productService.getProductById(productId);
        System.out.println("查询结果: " + product);
    }
}
