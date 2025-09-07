package com.pattern.adapter.yuantong;

import java.util.List;

/**
 * 圆通第三方物流 API：已存在的、不能修改的 adaptee。
 *
 * <p>模拟圆通提供的 SDK，参数顺序、返回结构均与顺丰不同。</p>
 */
public class YuantongLogisticsApi {

    /**
     * 圆通风格的查询方法。
     */
    public YuantongResponse queryTrace(String mailNo, String mobile) {
        // 模拟调用圆通接口
        return new YuantongResponse(
                mailNo,
                "YT",
                List.of(new Trace("已签收", "2024-08-01 14:30:00")),
                "3",
                "已签收"
        );
    }

    /**
     * 圆通响应对象（模拟第三方格式）。
     */
    public record YuantongResponse(
            String mailNo,
            String expCode,
            List<Trace> data,
            String statusCode,
            String statusInfo
    ) {
    }

    public record Trace(String context, String time) {
    }
}
