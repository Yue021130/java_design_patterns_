package com.pattern.adapter.shunfeng;

import java.time.LocalDateTime;

/**
 * 顺丰第三方物流 API：已存在的、不能修改的 adaptee。
 *
 * <p>模拟顺丰提供的 SDK，返回格式与内部系统不一致。</p>
 */
public class ShunfengLogisticsApi {

    /**
     * 顺丰风格的查询方法。
     */
    public ShunfengResponse queryByWaybillNo(String waybillNo, String customerCode) {
        // 模拟调用顺丰接口
        return new ShunfengResponse(
                waybillNo,
                "SF1234567890",
                "80",
                "快件已签收",
                LocalDateTime.now().toString(),
                true
        );
    }

    /**
     * 顺丰响应对象（模拟第三方格式）。
     */
    public record ShunfengResponse(
            String waybillNo,
            String sfOrderNo,
            String routeCode,
            String routeName,
            String acceptTime,
            boolean signed
    ) {
    }
}
