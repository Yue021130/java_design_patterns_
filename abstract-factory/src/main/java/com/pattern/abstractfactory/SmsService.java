package com.pattern.abstractfactory;

import java.util.Map;

/**
 * 短信服务：抽象产品 B。
 */
public interface SmsService {

    String send(String phone, String templateCode, Map<String, String> params);
}
