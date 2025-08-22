package com.pattern.builder;

import java.time.temporal.ChronoUnit;

/**
 * 时间窗口（Java 17 Record）。
 *
 * <p>用于风控规则中的“N 分钟内发生 M 次”类条件。</p>
 */
public record TimeWindow(int duration, ChronoUnit unit) {

    public TimeWindow {
        if (duration <= 0) {
            throw new IllegalArgumentException("时间窗口 duration 必须大于 0");
        }
        if (unit == null) {
            throw new IllegalArgumentException("时间单位 unit 不能为空");
        }
    }
}
