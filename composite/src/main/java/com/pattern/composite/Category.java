package com.pattern.composite;

/**
 * 组件接口：商品类目。
 *
 * <p>组合模式的核心思想：让叶子节点和组合节点对外暴露一致的接口，
 * 客户端无需区分“单个类目”和“类目树”。</p>
 */
public interface Category {

    String getId();

    String getName();

    /**
     * 获取该类目下的商品数量。
     * 叶子节点返回自身数量；组合节点递归汇总子节点数量。
     */
    int getProductCount();

    /**
     * 添加子类目（只有组合节点支持，默认抛出异常）。
     */
    default void add(Category category) {
        throw new UnsupportedOperationException("叶子类目不支持添加子类目");
    }

    /**
     * 移除子类目（只有组合节点支持，默认抛出异常）。
     */
    default void remove(Category category) {
        throw new UnsupportedOperationException("叶子类目不支持移除子类目");
    }

    /**
     * 打印类目结构。
     */
    void display(String indent);
}
