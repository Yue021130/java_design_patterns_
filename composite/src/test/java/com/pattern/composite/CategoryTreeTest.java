package com.pattern.composite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTreeTest {

    @Test
    void leafCategoryShouldReturnItsOwnProductCount() {
        Category leaf = new LeafCategory("L-001", "iPhone", 120);
        assertEquals(120, leaf.getProductCount());
    }

    @Test
    void compositeCategoryShouldSumChildrenProductCount() {
        Category phones = new CompositeCategory("C-001", "手机");
        phones.add(new LeafCategory("L-001", "iPhone", 120));
        phones.add(new LeafCategory("L-002", "Xiaomi", 200));

        assertEquals(320, phones.getProductCount());
    }

    @Test
    void nestedCompositeShouldRecursivelySumProductCount() {
        Category electronics = new CompositeCategory("C-001", "数码家电");
        Category phones = new CompositeCategory("C-002", "手机通讯");
        Category computers = new CompositeCategory("C-003", "电脑办公");

        phones.add(new LeafCategory("L-001", "iPhone", 120));
        phones.add(new LeafCategory("L-002", "Xiaomi", 200));
        computers.add(new LeafCategory("L-003", "MacBook", 80));
        computers.add(new LeafCategory("L-004", "Dell", 60));

        electronics.add(phones);
        electronics.add(computers);

        assertEquals(460, electronics.getProductCount());
        assertEquals(320, phones.getProductCount());
        assertEquals(140, computers.getProductCount());
    }

    @Test
    void removeChildShouldUpdateProductCount() {
        Category phones = new CompositeCategory("C-001", "手机");
        Category iphone = new LeafCategory("L-001", "iPhone", 120);
        Category xiaomi = new LeafCategory("L-002", "Xiaomi", 200);
        phones.add(iphone);
        phones.add(xiaomi);

        phones.remove(iphone);

        assertEquals(200, phones.getProductCount());
    }

    @Test
    void leafShouldNotSupportAddOrRemove() {
        Category leaf = new LeafCategory("L-001", "iPhone", 120);
        assertThrows(UnsupportedOperationException.class, () -> leaf.add(leaf));
        assertThrows(UnsupportedOperationException.class, () -> leaf.remove(leaf));
    }

    @Test
    void clientServiceShouldTreatLeafAndCompositeUniformly() {
        CategoryService service = new CategoryService();
        Category leaf = new LeafCategory("L-001", "单品", 50);

        Category composite = new CompositeCategory("C-001", "组合");
        composite.add(new LeafCategory("L-002", "子项1", 30));
        composite.add(new LeafCategory("L-003", "子项2", 20));

        assertEquals(50, service.countProducts(leaf));
        assertEquals(50, service.countProducts(composite));
    }
}
