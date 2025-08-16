package com.pattern.abstractfactory;

import com.pattern.abstractfactory.aliyun.AliyunCloudServiceFactory;
import com.pattern.abstractfactory.aws.AwsCloudServiceFactory;

import java.util.Map;

/**
 * 抽象工厂模式客户端入口。
 */
public class AbstractFactoryDemo {

    public static void main(String[] args) {
        // 根据部署区域切换云厂商
        CloudServiceFactory factory = selectFactory("aliyun");

        SaaSPlatform platform = new SaaSPlatform(factory);

        String url = platform.uploadFile("my-bucket", "report.pdf", new byte[1024]);
        System.out.println("上传结果: " + url);

        String smsResult = platform.sendNotification("13800138000", "ORDER_SUCCESS",
                Map.of("orderNo", "ORD-001", "amount", "199.99"));
        System.out.println("短信结果: " + smsResult);

        platform.pushEvent("order-topic", "order created");
    }

    private static CloudServiceFactory selectFactory(String region) {
        return switch (region) {
            case "aliyun" -> new AliyunCloudServiceFactory(
                    "LTAI123456", "secret123", "cn-hangzhou");
            case "aws" -> new AwsCloudServiceFactory(
                    "AKIA123456", "aws-secret", "ap-southeast-1");
            default -> throw new UnsupportedOperationException("不支持的云厂商: " + region);
        };
    }
}
