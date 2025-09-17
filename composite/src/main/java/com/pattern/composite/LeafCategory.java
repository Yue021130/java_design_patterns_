package com.pattern.composite;

/**
 * 叶子节点：没有子类目的末端类目。
 */
public class LeafCategory implements Category {

    private final String id;
    private final String name;
    private final int productCount;

    public LeafCategory(String id, String name, int productCount) {
        this.id = id;
        this.name = name;
        this.productCount = productCount;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getProductCount() {
        return productCount;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "- " + name + " (" + productCount + " 件商品) [叶子]");
    }
}
