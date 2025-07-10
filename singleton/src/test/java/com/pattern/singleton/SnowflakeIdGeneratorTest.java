package com.pattern.singleton;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SnowflakeIdGeneratorTest {

    @Test
    void shouldReturnSameInstance() {
        var first = SnowflakeIdGenerator.getInstance(1L, 1L);
        var second = SnowflakeIdGenerator.getInstance(1L, 1L);
        assertSame(first, second, "两次获取的实例必须相同");
    }

    @Test
    void shouldGenerateUniqueIdsUnderConcurrency() throws InterruptedException {
        var generator = SnowflakeIdGenerator.getInstance(1L, 1L);
        Set<Long> idSet = ConcurrentHashMap.newKeySet();

        int threadCount = 16;
        int idsPerThread = 1000;

        var executor = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                IntStream.range(0, idsPerThread)
                        .mapToObj(idx -> generator.nextId())
                        .forEach(idSet::add);
            });
        }

        executor.shutdown();
        boolean terminated = executor.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
        assertTrue(terminated, "线程池应在 5 秒内完成全部任务");

        assertEquals(threadCount * idsPerThread, idSet.size(),
                "并发生成的 ID 必须全部唯一");
    }
}
