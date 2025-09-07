package com.pattern.adapter;

import java.util.List;

/**
 * 物流平台：上层业务客户端，只依赖统一的 {@link LogisticsQueryService}。
 *
 * <p>在 Spring 项目中，通常由容器注入所有适配器实例。</p>
 */
public class LogisticsPlatform {

    private final List<LogisticsQueryService> adapters;

    public LogisticsPlatform(List<LogisticsQueryService> adapters) {
        this.adapters = List.copyOf(adapters);
    }

    /**
     * 根据承运商标识，找到对应适配器并查询。
     */
    public LogisticsQueryService.LogisticsResult query(
            LogisticsQueryService.QueryRequest request) {
        return adapters.stream()
                .filter(adapter -> adapter.supports(request.carrier()))
                .findFirst()
                .map(adapter -> adapter.queryLogistics(request))
                .orElseThrow(() -> new UnsupportedOperationException(
                        "不支持的承运商: " + request.carrier()));
    }
}
