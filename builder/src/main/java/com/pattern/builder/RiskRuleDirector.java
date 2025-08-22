package com.pattern.builder;

import java.time.temporal.ChronoUnit;

/**
 * 风控规则指挥者（Director）：封装常见规则的预配置构建流程。
 *
 * <p>Director 不是建造者模式的必需角色，但当某些复杂对象的构建步骤相对固定时，
 * 用 Director 可以进一步简化客户端代码。</p>
 */
public class RiskRuleDirector {

    /**
     * 创建一个“高频小额刷单”规则。
     */
    public RiskRule createHighFrequencyRule(String ruleId) {
        return RiskRule.builder()
                .ruleId(ruleId)
                .ruleName("高频小额刷单风控")
                .amountThreshold(100)
                .frequencyThreshold(5)
                .timeWindow(10, ChronoUnit.MINUTES)
                .addRegion("ABNORMAL")
                .priority(1)
                .build();
    }

    /**
     * 创建一个“大额异常交易”规则。
     */
    public RiskRule createLargeAmountRule(String ruleId) {
        return RiskRule.builder()
                .ruleId(ruleId)
                .ruleName("大额异常交易风控")
                .amountThreshold(50000)
                .frequencyThreshold(1)
                .timeWindow(1, ChronoUnit.HOURS)
                .addBlacklist("SUSPICIOUS_MERCHANT")
                .priority(1)
                .build();
    }
}
