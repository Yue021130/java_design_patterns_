package com.pattern.prototype;

/**
 * 原型接口：声明 copy 方法。
 *
 * <p>不强制使用 JDK 的 {@link Cloneable}，因为该接口语义混乱（缺少 public clone 方法），
 * 现代 Java 更推荐显式定义 copy 方法或拷贝构造器。</p>
 */
public interface Prototype<T extends Prototype<T>> {

    /**
     * 创建并返回当前对象的深度拷贝。
     */
    T copy();
}
