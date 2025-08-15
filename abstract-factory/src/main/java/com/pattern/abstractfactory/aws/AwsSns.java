package com.pattern.abstractfactory.aws;

import com.pattern.abstractfactory.SmsService;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * AWS SNS：具体产品 B2。
 */
public class AwsSns implements SmsService {

    private final String accessKeyId;
    private final String secretAccessKey;
    private final String region;

    public AwsSns(String accessKeyId, String secretAccessKey, String region) {
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.region = region;
    }

    @Override
    public String send(String phone, String templateCode, Map<String, String> params) {
        String paramStr = params.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
        return "[AWS-SNS] send to " + phone + ", template=" + templateCode
                + ", params={" + paramStr + "}, region=" + region;
    }
}
