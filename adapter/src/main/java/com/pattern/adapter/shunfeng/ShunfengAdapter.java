package com.pattern.adapter.shunfeng;

import com.pattern.adapter.LogisticsQueryService;

/**
 * 顺丰物流查询适配器：把顺丰 API 适配为内部统一的 {@link LogisticsQueryService}。
 */
public class ShunfengAdapter implements LogisticsQueryService {

    private final ShunfengLogisticsApi shunfengApi;

    public ShunfengAdapter(ShunfengLogisticsApi shunfengApi) {
        this.shunfengApi = shunfengApi;
    }

    @Override
    public LogisticsResult queryLogistics(QueryRequest request) {
        // 模拟从 request 中获取 customerCode（实际可能从配置或用户信息中取得）
        String customerCode = "CUST_001";
        ShunfengLogisticsApi.ShunfengResponse response =
                shunfengApi.queryByWaybillNo(request.trackingNumber(), customerCode);

        return new LogisticsResult(
                response.waybillNo(),
                "SHUNFENG",
                mapStatus(response.routeCode()),
                response.routeName() + " [" + response.acceptTime() + "]",
                response.signed()
        );
    }

    @Override
    public boolean supports(String carrier) {
        return "SHUNFENG".equalsIgnoreCase(carrier);
    }

    private String mapStatus(String routeCode) {
        return switch (routeCode) {
            case "10" -> "已揽收";
            case "30", "40" -> "运输中";
            case "50" -> "派送中";
            case "80" -> "已签收";
            default -> "未知";
        };
    }
}
