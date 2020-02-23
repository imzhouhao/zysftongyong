package com.boot.zysf.api.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CountableThreadPool {
    public static ConcurrentMap<String, Long> countMap = new ConcurrentHashMap<>(20335);
    private int threadNum;
    private AtomicInteger threadAlive = new AtomicInteger();
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition;
    private ExecutorService executorService;

    public CountableThreadPool(int threadNum) {
        this.condition = this.reentrantLock.newCondition();
        this.threadNum = threadNum;
        this.executorService = Executors.newFixedThreadPool(threadNum);
    }

    public CountableThreadPool(int threadNum, ExecutorService executorService) {
        this.condition = this.reentrantLock.newCondition();
        this.threadNum = threadNum;
        this.executorService = executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public int getThreadAlive() {
        return this.threadAlive.get();
    }

    public int getThreadNum() {
        return this.threadNum;
    }

    public void execute(final Runnable runnable) {
        if (this.threadAlive.get() >= this.threadNum) {
            try {
                this.reentrantLock.lock();

                while(this.threadAlive.get() >= this.threadNum) {
                    try {
                        this.condition.await();
                    } catch (InterruptedException var6) {
                        ;
                    }
                }
            } finally {
                this.reentrantLock.unlock();
            }
        }

        this.threadAlive.incrementAndGet();
        this.executorService.execute(new Runnable() {
            public void run() {
                try {
                    runnable.run();
                } finally {
                    try {
                        CountableThreadPool.this.reentrantLock.lock();
                        CountableThreadPool.this.threadAlive.decrementAndGet();
                        CountableThreadPool.this.condition.signal();
                    } finally {
                        CountableThreadPool.this.reentrantLock.unlock();
                    }
                }

            }
        });
    }

    public boolean isShutdown() {
        return this.executorService.isShutdown();
    }

    public void shutdown() {
        this.executorService.shutdown();
    }
}
