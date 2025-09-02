package com.pattern.prototype;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 商品模板服务：管理模板库，支持基于模板快速克隆新商品。
 *
 * <p>在电商后台，运营人员可以维护一组标准商品模板；商家上架时选择模板并做少量修改即可发布。</p>
 */
public class ProductTemplateService {

    private final Map<String, ProductTemplate> templates = new HashMap<>();

    public void registerTemplate(ProductTemplate template) {
        Objects.requireNonNull(template, "模板不能为空");
        templates.put(template.getProductId(), template);
    }

    /**
     * 基于模板克隆出一个新商品，可重新指定 productId 和 productName。
     */
    public ProductTemplate createProductFromTemplate(String templateId,
                                                     String newProductId,
                                                     String newProductName) {
        ProductTemplate template = templates.get(templateId);
        if (template == null) {
            throw new IllegalArgumentException("模板不存在: " + templateId);
        }
        ProductTemplate product = template.copy();
        product.setProductId(newProductId);
        product.setProductName(newProductName);
        return product;
    }

    public boolean hasTemplate(String templateId) {
        return templates.containsKey(templateId);
    }
}
