package com.pattern.adapter;

import com.pattern.adapter.shunfeng.ShunfengAdapter;
import com.pattern.adapter.shunfeng.ShunfengLogisticsApi;
import com.pattern.adapter.yuantong.YuantongAdapter;
import com.pattern.adapter.yuantong.YuantongLogisticsApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogisticsAdapterTest {

    private LogisticsPlatform platform;

    @BeforeEach
    void setUp() {
        List<LogisticsQueryService> adapters = List.of(
                new ShunfengAdapter(new ShunfengLogisticsApi()),
                new YuantongAdapter(new YuantongLogisticsApi())
        );
        platform = new LogisticsPlatform(adapters);
    }

    @Test
    void shouldQueryShunfengViaAdapter() {
        var request = new LogisticsQueryService.QueryRequest(
                "SF123456789", "SHUNFENG", "13800138000");
        var result = platform.query(request);

        assertEquals("SF123456789", result.trackingNumber());
        assertEquals("SHUNFENG", result.carrier());
        assertEquals("已签收", result.status());
        assertTrue(result.delivered());
    }

    @Test
    void shouldQueryYuantongViaAdapter() {
        var request = new LogisticsQueryService.QueryRequest(
                "YT987654321", "YUANTONG", "13800138000");
        var result = platform.query(request);

        assertEquals("YT987654321", result.trackingNumber());
        assertEquals("YUANTONG", result.carrier());
        assertEquals("已签收", result.status());
        assertTrue(result.delivered());
    }

    @Test
    void shouldThrowForUnsupportedCarrier() {
        var request = new LogisticsQueryService.QueryRequest(
                "UNKNOWN", "DHL", "13800138000");
        assertThrows(UnsupportedOperationException.class, () -> platform.query(request));
    }

    @Test
    void shunfengAdapterShouldSupportOnlyShunfeng() {
        var adapter = new ShunfengAdapter(new ShunfengLogisticsApi());
        assertTrue(adapter.supports("SHUNFENG"));
        assertFalse(adapter.supports("YUANTONG"));
    }

    @Test
    void yuantongAdapterShouldSupportOnlyYuantong() {
        var adapter = new YuantongAdapter(new YuantongLogisticsApi());
        assertTrue(adapter.supports("YUANTONG"));
        assertFalse(adapter.supports("SHUNFENG"));
    }
}
