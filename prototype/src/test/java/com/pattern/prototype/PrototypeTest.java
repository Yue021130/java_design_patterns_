package com.pattern.prototype;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PrototypeTest {

    @Test
    void skuCopyShouldBeIndependentInstance() {
        SkuTemplate original = new SkuTemplate(
                "SKU-001", "Original", new BigDecimal("100"),
                Map.of("color", "red"), List.of("img1.jpg"),
                new ShippingTemplate("ST-001", "包邮", 1.0, 0.0));

        SkuTemplate copy = original.copy();

        assertNotSame(original, copy);
        assertEquals(original.getSkuId(), copy.getSkuId());
    }

    @Test
    void skuCopyShouldDeepCopyCollections() {
        SkuTemplate original = new SkuTemplate(
                "SKU-001", "Original", new BigDecimal("100"),
                new java.util.HashMap<>(Map.of("color", "red")),
                new java.util.ArrayList<>(List.of("img1.jpg")),
                new ShippingTemplate("ST-001", "包邮", 1.0, 0.0));

        SkuTemplate copy = original.copy();

        assertNotSame(original.getAttributes(), copy.getAttributes());
        assertNotSame(original.getImages(), copy.getImages());

        copy.getAttributes().put("size", "L");
        copy.getImages().add("img2.jpg");

        assertEquals(1, original.getAttributes().size());
        assertEquals(1, original.getImages().size());
        assertEquals(2, copy.getAttributes().size());
        assertEquals(2, copy.getImages().size());
    }

    @Test
    void skuCopyShouldDeepCopyNestedObject() {
        ShippingTemplate shipping = new ShippingTemplate("ST-001", "包邮", 1.0, 0.0);
        SkuTemplate original = new SkuTemplate(
                "SKU-001", "Original", new BigDecimal("100"),
                Map.of(), List.of(), shipping);

        SkuTemplate copy = original.copy();

        assertNotSame(original.getShippingTemplate(), copy.getShippingTemplate());
        copy.getShippingTemplate().setTemplateName("顺丰包邮");
        assertEquals("包邮", original.getShippingTemplate().getTemplateName());
    }

    @Test
    void productCopyShouldDeepCopyAllSkus() {
        SkuTemplate sku1 = new SkuTemplate(
                "SKU-TPL-001", "SKU1", new BigDecimal("100"),
                Map.of(), List.of(), null);
        ProductTemplate original = new ProductTemplate(
                "TPL-001", "Template", "Category", List.of(sku1));

        ProductTemplate copy = original.copy();

        assertNotSame(original, copy);
        assertNotSame(original.getSkus(), copy.getSkus());
        assertNotSame(original.getSkus().get(0), copy.getSkus().get(0));
    }

    @Test
    void serviceShouldCreateProductFromTemplate() {
        SkuTemplate sku = new SkuTemplate(
                "SKU-TPL-001", "标准款", new BigDecimal("99"),
                Map.of("color", "白色"), List.of("img1.jpg"),
                new ShippingTemplate("ST-001", "包邮", 1.0, 0.0));
        ProductTemplate template = new ProductTemplate(
                "TPL-001", "夏季T恤", "服装", List.of(sku));

        ProductTemplateService service = new ProductTemplateService();
        service.registerTemplate(template);

        ProductTemplate newProduct = service.createProductFromTemplate(
                "TPL-001", "P-001", "店铺定制T恤");

        assertEquals("P-001", newProduct.getProductId());
        assertEquals("店铺定制T恤", newProduct.getProductName());
        assertNotSame(template, newProduct);
    }

    @Test
    void serviceShouldThrowForMissingTemplate() {
        ProductTemplateService service = new ProductTemplateService();
        assertThrows(IllegalArgumentException.class, () ->
                service.createProductFromTemplate("NOT_EXIST", "P-001", "Name"));
    }
}
