package com.pattern.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 组合节点：包含子类目的类目。
 */
public class CompositeCategory implements Category {

    private final String id;
    private final String name;
    private final List<Category> children = new ArrayList<>();

    public CompositeCategory(String id, String name) {
        this.id = id;
        this.name = name;
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
        return children.stream()
                .mapToInt(Category::getProductCount)
                .sum();
    }

    @Override
    public void add(Category category) {
        children.add(category);
    }

    @Override
    public void remove(Category category) {
        children.remove(category);
    }

    public List<Category> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "+ " + name + " (" + getProductCount() + " 件商品) [组合]");
        for (Category child : children) {
            child.display(indent + "  ");
        }
    }
}
