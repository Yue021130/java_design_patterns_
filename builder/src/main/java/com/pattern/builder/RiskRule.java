package com.pattern.builder;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 风控规则（产品）：字段多、部分必填、部分可选，非常适合建造者模式。
 *
 * <p>使用 Java 17 Record 作为不可变产品，由内部 Builder 负责一步步组装。</p>
 */
public record RiskRule(
        String ruleId,
        String ruleName,
        BigDecimal amountThreshold,
        Integer frequencyThreshold,
        TimeWindow timeWindow,
        Set<String> blacklists,
        Set<String> regions,
        boolean enabled,
        Integer priority
) {

    public RiskRule {
        Objects.requireNonNull(ruleId, "ruleId 不能为空");
        Objects.requireNonNull(ruleName, "ruleName 不能为空");
        blacklists = Set.copyOf(blacklists != null ? blacklists : Set.of());
        regions = Set.copyOf(regions != null ? regions : Set.of());
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * 风控规则建造者。
     */
    public static class Builder {

        private String ruleId;
        private String ruleName;
        private BigDecimal amountThreshold;
        private Integer frequencyThreshold;
        private TimeWindow timeWindow;
        private Set<String> blacklists = new HashSet<>();
        private Set<String> regions = new HashSet<>();
        private boolean enabled = true;
        private Integer priority = 5;

        public Builder ruleId(String ruleId) {
            this.ruleId = ruleId;
            return this;
        }

        public Builder ruleName(String ruleName) {
            this.ruleName = ruleName;
            return this;
        }

        public Builder amountThreshold(BigDecimal amountThreshold) {
            this.amountThreshold = amountThreshold;
            return this;
        }

        public Builder amountThreshold(double amountThreshold) {
            this.amountThreshold = BigDecimal.valueOf(amountThreshold);
            return this;
        }

        public Builder frequencyThreshold(Integer frequencyThreshold) {
            this.frequencyThreshold = frequencyThreshold;
            return this;
        }

        public Builder timeWindow(TimeWindow timeWindow) {
            this.timeWindow = timeWindow;
            return this;
        }

        public Builder timeWindow(int duration, java.time.temporal.ChronoUnit unit) {
            this.timeWindow = new TimeWindow(duration, unit);
            return this;
        }

        public Builder addBlacklist(String blacklist) {
            this.blacklists.add(blacklist);
            return this;
        }

        public Builder blacklists(Set<String> blacklists) {
            this.blacklists = new HashSet<>(blacklists);
            return this;
        }

        public Builder addRegion(String region) {
            this.regions.add(region);
            return this;
        }

        public Builder regions(Set<String> regions) {
            this.regions = new HashSet<>(regions);
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder priority(Integer priority) {
            this.priority = priority;
            return this;
        }

        /**
         * 构建最终不可变的风控规则对象。
         */
        public RiskRule build() {
            if (ruleId == null || ruleId.isBlank()) {
                throw new IllegalStateException("ruleId 必填");
            }
            if (ruleName == null || ruleName.isBlank()) {
                throw new IllegalStateException("ruleName 必填");
            }
            return new RiskRule(ruleId, ruleName, amountThreshold, frequencyThreshold,
                    timeWindow, blacklists, regions, enabled, priority);
        }
    }
}
