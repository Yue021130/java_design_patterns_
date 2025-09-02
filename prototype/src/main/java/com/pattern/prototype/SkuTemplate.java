package com.pattern.prototype;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * SKU 模板：具体原型。
 *
 * <p>包含基本类型、String、List、Map 和嵌套引用类型，用于展示深拷贝的重要性。</p>
 */
public class SkuTemplate implements Prototype<SkuTemplate> {

    private String skuId;
    private String title;
    private BigDecimal price;
    private Map<String, String> attributes;
    private List<String> images;
    private ShippingTemplate shippingTemplate;

    public SkuTemplate(String skuId, String title, BigDecimal price,
                       Map<String, String> attributes, List<String> images,
                       ShippingTemplate shippingTemplate) {
        this.skuId = skuId;
        this.title = title;
        this.price = price;
        this.attributes = Objects.requireNonNullElse(attributes, new HashMap<>());
        this.images = Objects.requireNonNullElse(images, new ArrayList<>());
        this.shippingTemplate = shippingTemplate;
    }

    /**
     * 拷贝构造器：实现深拷贝。
     */
    public SkuTemplate(SkuTemplate source) {
        this.skuId = source.skuId;
        this.title = source.title;
        this.price = source.price;
        // 深拷贝可变集合
        this.attributes = new HashMap<>(source.attributes);
        this.images = new ArrayList<>(source.images);
        // 深拷贝嵌套引用对象
        this.shippingTemplate = source.shippingTemplate != null
                ? source.shippingTemplate.copy()
                : null;
    }

    @Override
    public SkuTemplate copy() {
        return new SkuTemplate(this);
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public List<String> getImages() {
        return images;
    }

    public ShippingTemplate getShippingTemplate() {
        return shippingTemplate;
    }

    public void setShippingTemplate(ShippingTemplate shippingTemplate) {
        this.shippingTemplate = shippingTemplate;
    }

    @Override
    public String toString() {
        return "SkuTemplate{skuId='" + skuId + "', title='" + title + "', price=" + price
                + ", attributes=" + attributes + ", images=" + images
                + ", shippingTemplate=" + shippingTemplate + "}";
    }
}
