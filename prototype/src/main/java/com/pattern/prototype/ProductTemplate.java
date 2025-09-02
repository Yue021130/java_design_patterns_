package com.pattern.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品模板：包含多个 SKU 模板，演示复合对象的原型复制。
 */
public class ProductTemplate implements Prototype<ProductTemplate> {

    private String productId;
    private String productName;
    private String category;
    private List<SkuTemplate> skus;

    public ProductTemplate(String productId, String productName, String category,
                           List<SkuTemplate> skus) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.skus = skus != null ? skus : new ArrayList<>();
    }

    /**
     * 拷贝构造器：递归深拷贝所有 SKU。
     */
    public ProductTemplate(ProductTemplate source) {
        this.productId = source.productId;
        this.productName = source.productName;
        this.category = source.category;
        this.skus = new ArrayList<>();
        for (SkuTemplate sku : source.skus) {
            this.skus.add(sku.copy());
        }
    }

    @Override
    public ProductTemplate copy() {
        return new ProductTemplate(this);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<SkuTemplate> getSkus() {
        return skus;
    }

    @Override
    public String toString() {
        return "ProductTemplate{productId='" + productId + "', productName='" + productName
                + "', category='" + category + "', skus=" + skus + "}";
    }
}
