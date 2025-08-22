package com.pattern.builder;

import java.time.temporal.ChronoUnit;

/**
 * 建造者模式客户端入口。
 */
public class BuilderDemo {

    public static void main(String[] args) {
        // 方式 1：使用 Builder 自由组装
        RiskRule customRule = RiskRule.builder()
                .ruleId("RULE-001")
                .ruleName("新用户首单风控")
                .amountThreshold(999)
                .frequencyThreshold(3)
                .timeWindow(30, ChronoUnit.MINUTES)
                .addBlacklist("FRAUD_DEVICE")
                .addBlacklist("PROXY_IP")
                .addRegion("OVERSEAS")
                .priority(2)
                .enabled(true)
                .build();

        System.out.println("自定义规则: " + customRule);

        // 方式 2：使用 Director 快速创建常见规则
        RiskRuleDirector director = new RiskRuleDirector();
        RiskRule highFreqRule = director.createHighFrequencyRule("RULE-002");
        RiskRule largeAmountRule = director.createLargeAmountRule("RULE-003");

        System.out.println("高频规则: " + highFreqRule);
        System.out.println("大额规则: " + largeAmountRule);
    }
}
