package com.pattern.composite;

/**
 * 组合模式客户端入口。
 */
public class CompositeDemo {

    public static void main(String[] args) {
        // 构建商品类目树
        Category electronics = new CompositeCategory("C-001", "数码家电");
        Category phones = new CompositeCategory("C-002", "手机通讯");
        Category computers = new CompositeCategory("C-003", "电脑办公");

        Category iphone = new LeafCategory("C-004", "iPhone", 120);
        Category xiaomi = new LeafCategory("C-005", "小米手机", 200);
        Category macbook = new LeafCategory("C-006", "MacBook", 80);
        Category dell = new LeafCategory("C-007", "Dell", 60);

        phones.add(iphone);
        phones.add(xiaomi);
        computers.add(macbook);
        computers.add(dell);
        electronics.add(phones);
        electronics.add(computers);

        CategoryService service = new CategoryService();
        service.printCategoryTree(electronics);
        System.out.println("数码家电总商品数: " + service.countProducts(electronics));
    }
}
