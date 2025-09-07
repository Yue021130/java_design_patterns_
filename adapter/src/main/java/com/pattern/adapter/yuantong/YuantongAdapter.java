package com.pattern.adapter.yuantong;

import com.pattern.adapter.LogisticsQueryService;

/**
 * 圆通物流查询适配器：把圆通 API 适配为内部统一的 {@link LogisticsQueryService}。
 */
public class YuantongAdapter implements LogisticsQueryService {

    private final YuantongLogisticsApi yuantongApi;

    public YuantongAdapter(YuantongLogisticsApi yuantongApi) {
        this.yuantongApi = yuantongApi;
    }

    @Override
    public LogisticsResult queryLogistics(QueryRequest request) {
        YuantongLogisticsApi.YuantongResponse response =
                yuantongApi.queryTrace(request.trackingNumber(), request.phone());

        String latestUpdate = response.data().isEmpty()
                ? response.statusInfo()
                : response.data().get(0).context() + " [" + response.data().get(0).time() + "]";

        return new LogisticsResult(
                response.mailNo(),
                "YUANTONG",
                mapStatus(response.statusCode()),
                latestUpdate,
                "3".equals(response.statusCode())
        );
    }

    @Override
    public boolean supports(String carrier) {
        return "YUANTONG".equalsIgnoreCase(carrier);
    }

    private String mapStatus(String statusCode) {
        return switch (statusCode) {
            case "0" -> "已揽收";
            case "1", "2" -> "运输中";
            case "5" -> "派送中";
            case "3" -> "已签收";
            default -> "未知";
        };
    }
}
