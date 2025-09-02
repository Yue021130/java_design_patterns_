package com.pattern.prototype;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 原型模式客户端入口。
 */
public class PrototypeDemo {

    public static void main(String[] args) {
        // 1. 构建一个标准商品模板
        ShippingTemplate shipping = new ShippingTemplate(
                "ST-001", "全国包邮", 1.0, 0.0);

        SkuTemplate skuTemplate = new SkuTemplate(
                "SKU-TPL-001",
                "标准款 T 恤",
                new BigDecimal("99.00"),
                Map.of("color", "白色", "size", "L"),
                List.of("img1.jpg", "img2.jpg"),
                shipping
        );

        ProductTemplate template = new ProductTemplate(
                "TPL-001", "夏季基础款 T 恤", "服装", List.of(skuTemplate));

        // 2. 注册到模板服务
        ProductTemplateService service = new ProductTemplateService();
        service.registerTemplate(template);

        // 3. 基于模板克隆新商品，只修改少量信息即可上架
        ProductTemplate newProduct = service.createProductFromTemplate(
                "TPL-001", "P-20240731-001", "夏季基础款 T 恤-店铺定制版");

        // 4. 修改克隆后的 SKU，不影响原模板
        SkuTemplate newSku = newProduct.getSkus().get(0);
        newSku.setSkuId("SKU-001");
        newSku.setPrice(new BigDecimal("89.00"));
        newSku.getAttributes().put("custom", "刺绣 Logo");

        System.out.println("原模板: " + template);
        System.out.println("新商品: " + newProduct);
    }
}
