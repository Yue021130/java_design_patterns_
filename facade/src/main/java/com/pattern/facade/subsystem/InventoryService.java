package com.pattern.facade.subsystem;

import java.math.BigDecimal;

/**
 * 库存子系统。
 */
public class InventoryService {

    public boolean deductStock(String skuId, int quantity) {
        System.out.println("[库存] 扣减 SKU=" + skuId + "，数量=" + quantity);
        // 模拟库存充足
        return quantity <= 100;
    }

    public void rollbackStock(String skuId, int quantity) {
        System.out.println("[库存] 回滚 SKU=" + skuId + "，数量=" + quantity);
    }
}
