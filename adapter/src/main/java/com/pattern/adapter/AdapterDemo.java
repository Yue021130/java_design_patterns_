package com.pattern.adapter;

import com.pattern.adapter.shunfeng.ShunfengAdapter;
import com.pattern.adapter.shunfeng.ShunfengLogisticsApi;
import com.pattern.adapter.yuantong.YuantongAdapter;
import com.pattern.adapter.yuantong.YuantongLogisticsApi;

import java.util.List;

/**
 * 适配器模式客户端入口。
 */
public class AdapterDemo {

    public static void main(String[] args) {
        List<LogisticsQueryService> adapters = List.of(
                new ShunfengAdapter(new ShunfengLogisticsApi()),
                new YuantongAdapter(new YuantongLogisticsApi())
        );

        LogisticsPlatform platform = new LogisticsPlatform(adapters);

        var shunfengRequest = new LogisticsQueryService.QueryRequest(
                "SF123456789", "SHUNFENG", "13800138000");
        var yuantongRequest = new LogisticsQueryService.QueryRequest(
                "YT987654321", "YUANTONG", "13800138000");

        System.out.println("顺丰结果: " + platform.query(shunfengRequest));
        System.out.println("圆通结果: " + platform.query(yuantongRequest));
    }
}
