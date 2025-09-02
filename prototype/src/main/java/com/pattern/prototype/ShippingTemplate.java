package com.pattern.prototype;

/**
 * 运费模板：用于演示原型模式中的嵌套对象深拷贝。
 */
public class ShippingTemplate implements Prototype<ShippingTemplate> {

    private String templateId;
    private String templateName;
    private double baseWeight;
    private double basePrice;

    public ShippingTemplate(String templateId, String templateName,
                            double baseWeight, double basePrice) {
        this.templateId = templateId;
        this.templateName = templateName;
        this.baseWeight = baseWeight;
        this.basePrice = basePrice;
    }

    /**
     * 拷贝构造器。
     */
    public ShippingTemplate(ShippingTemplate source) {
        this.templateId = source.templateId;
        this.templateName = source.templateName;
        this.baseWeight = source.baseWeight;
        this.basePrice = source.basePrice;
    }

    @Override
    public ShippingTemplate copy() {
        return new ShippingTemplate(this);
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public double getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(double baseWeight) {
        this.baseWeight = baseWeight;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "ShippingTemplate{templateId='" + templateId + "', templateName='" + templateName
                + "', baseWeight=" + baseWeight + ", basePrice=" + basePrice + "}";
    }
}
