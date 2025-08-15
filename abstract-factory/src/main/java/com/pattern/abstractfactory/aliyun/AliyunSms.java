package com.pattern.abstractfactory.aliyun;

import com.pattern.abstractfactory.SmsService;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 阿里云短信：具体产品 B1。
 */
public class AliyunSms implements SmsService {

    private final String accessKeyId;
    private final String accessKeySecret;
    private final String region;

    public AliyunSms(String accessKeyId, String accessKeySecret, String region) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.region = region;
    }

    @Override
    public String send(String phone, String templateCode, Map<String, String> params) {
        String paramStr = params.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
        return "[AliyunSMS] send to " + phone + ", template=" + templateCode
                + ", params={" + paramStr + "}, region=" + region;
    }
}
