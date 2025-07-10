package com.pattern.singleton;

import java.time.Instant;

/**
 * 分布式 ID 生成器：雪花算法单例实现。
 *
 * <p>适用场景：电商订单号、支付流水号、全局唯一消息 ID 等需要保证进程内唯一且状态统一的场景。</p>
 *
 * <p>关键点：</p>
 * <ul>
 *   <li>volatile 禁止指令重排，确保多线程安全；</li>
 *   <li>private 构造器防止外部 new；</li>
 *   <li>使用 System.nanoTime 做简单时钟回拨保护（生产请配合 NTP 监控）。</li>
 * </ul>
 */
public final class SnowflakeIdGenerator {

    private static final long EPOCH = Instant.parse("2024-01-01T00:00:00Z").toEpochMilli();

    private static final long WORKER_ID_BITS = 5L;
    private static final long DATACENTER_ID_BITS = 5L;
    private static final long SEQUENCE_BITS = 12L;

    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);

    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    // volatile 保证可见性，并禁止 new 对象时的指令重排（happens-before）
    private static volatile SnowflakeIdGenerator INSTANCE;

    private final long workerId;
    private final long datacenterId;

    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private SnowflakeIdGenerator(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("worker Id 越界: " + workerId);
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException("datacenter Id 越界: " + datacenterId);
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 双重检查锁（Double-Checked Locking）获取单例。
     */
    public static SnowflakeIdGenerator getInstance(long workerId, long datacenterId) {
        if (INSTANCE == null) {                              // 第一次检查，避免不必要的同步
            synchronized (SnowflakeIdGenerator.class) {
                if (INSTANCE == null) {                      // 第二次检查，确保只创建一次
                    INSTANCE = new SnowflakeIdGenerator(workerId, datacenterId);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 生成下一个唯一 ID。
     */
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("时钟回拨，拒绝生成 ID，差值: " + (lastTimestamp - timestamp) + "ms");
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - EPOCH) << TIMESTAMP_LEFT_SHIFT)
                | (datacenterId << DATACENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
