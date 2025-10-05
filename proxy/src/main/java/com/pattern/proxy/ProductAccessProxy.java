package com.pattern.proxy;

import java.util.Set;

/**
 * 保护代理：控制对商品管理操作的访问权限。
 *
 * <p>演示代理模式的另一种用途：权限校验。</p>
 */
public class ProductAccessProxy implements ProductService {

    private final ProductService realService;
    private final Set<String> adminUsers;

    public ProductAccessProxy(ProductService realService, Set<String> adminUsers) {
        this.realService = realService;
        this.adminUsers = Set.copyOf(adminUsers);
    }

    @Override
    public Product getProductById(String productId) {
        // 查询操作允许所有人
        return realService.getProductById(productId);
    }

    @Override
    public void updateStock(String productId, int stock) {
        // 这里简化处理：把当前线程名作为用户标识
        String currentUser = Thread.currentThread().getName();
        if (!adminUsers.contains(currentUser)) {
            throw new SecurityException("用户 " + currentUser + " 无权限修改库存");
        }
        realService.updateStock(productId, stock);
    }
}
