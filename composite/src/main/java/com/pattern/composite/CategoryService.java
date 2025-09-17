package com.pattern.composite;

/**
 * 类目服务：客户端代码，统一处理叶子和组合类目。
 */
public class CategoryService {

    /**
     * 统计指定类目（可能是叶子也可能是组合）下的商品总数。
     */
    public int countProducts(Category category) {
        return category.getProductCount();
    }

    /**
     * 打印类目树。
     */
    public void printCategoryTree(Category category) {
        category.display("");
    }
}
