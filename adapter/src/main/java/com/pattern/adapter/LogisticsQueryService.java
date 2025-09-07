package com.pattern.adapter;

/**
 * 目标接口：系统内部统一的物流查询服务。
 *
 * <p>无论底层对接顺丰、圆通还是其他物流公司，上层业务都只依赖此接口。</p>
 */
public interface LogisticsQueryService {

    /**
     * 查询物流轨迹。
     *
     * @param request 查询请求
     * @return 标准化物流结果
     */
    LogisticsResult queryLogistics(QueryRequest request);

    /**
     * 是否支持该承运商。
     */
    boolean supports(String carrier);

    /**
     * 查询请求（Java 17 Record）。
     */
    record QueryRequest(String trackingNumber, String carrier, String phone) {
    }

    /**
     * 标准化物流结果（Java 17 Record）。
     */
    record LogisticsResult(
            String trackingNumber,
            String carrier,
            String status,
            String latestUpdate,
            boolean delivered
    ) {
    }
}
