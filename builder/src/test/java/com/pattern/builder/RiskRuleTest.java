package com.pattern.builder;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RiskRuleTest {

    @Test
    void shouldBuildMinimalRiskRule() {
        RiskRule rule = RiskRule.builder()
                .ruleId("RULE-MIN")
                .ruleName("最小规则")
                .build();

        assertEquals("RULE-MIN", rule.ruleId());
        assertEquals("最小规则", rule.ruleName());
        assertTrue(rule.enabled());
        assertEquals(5, rule.priority());
        assertTrue(rule.blacklists().isEmpty());
    }

    @Test
    void shouldBuildComplexRiskRule() {
        RiskRule rule = RiskRule.builder()
                .ruleId("RULE-001")
                .ruleName("复杂规则")
                .amountThreshold(new BigDecimal("1000.50"))
                .frequencyThreshold(5)
                .timeWindow(10, ChronoUnit.MINUTES)
                .blacklists(Set.of("BLACK_1", "BLACK_2"))
                .regions(Set.of("REGION_A", "REGION_B"))
                .enabled(false)
                .priority(1)
                .build();

        assertEquals(new BigDecimal("1000.50"), rule.amountThreshold());
        assertEquals(5, rule.frequencyThreshold());
        assertEquals(new TimeWindow(10, ChronoUnit.MINUTES), rule.timeWindow());
        assertEquals(Set.of("BLACK_1", "BLACK_2"), rule.blacklists());
        assertEquals(Set.of("REGION_A", "REGION_B"), rule.regions());
        assertFalse(rule.enabled());
        assertEquals(1, rule.priority());
    }

    @Test
    void shouldRejectMissingRuleId() {
        assertThrows(IllegalStateException.class, () ->
                RiskRule.builder()
                        .ruleName("缺少ID")
                        .build()
        );
    }

    @Test
    void shouldRejectMissingRuleName() {
        assertThrows(IllegalStateException.class, () ->
                RiskRule.builder()
                        .ruleId("RULE-X")
                        .build()
        );
    }

    @Test
    void directorShouldCreatePredefinedRules() {
        RiskRuleDirector director = new RiskRuleDirector();

        RiskRule highFreq = director.createHighFrequencyRule("RULE-HF");
        assertEquals("高频小额刷单风控", highFreq.ruleName());
        assertEquals(5, highFreq.frequencyThreshold());

        RiskRule largeAmount = director.createLargeAmountRule("RULE-LA");
        assertEquals("大额异常交易风控", largeAmount.ruleName());
        assertTrue(largeAmount.blacklists().contains("SUSPICIOUS_MERCHANT"));
    }

    @Test
    void builtRuleShouldBeImmutable() {
        Set<String> blacklistInput = new java.util.HashSet<>();
        blacklistInput.add("A");

        RiskRule rule = RiskRule.builder()
                .ruleId("RULE-IMM")
                .ruleName("不可变测试")
                .blacklists(blacklistInput)
                .build();

        blacklistInput.add("B");
        assertEquals(Set.of("A"), rule.blacklists());

        assertThrows(UnsupportedOperationException.class, () ->
                rule.blacklists().add("C")
        );
    }
}
