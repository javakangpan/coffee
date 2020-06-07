package com.kangpan.enums;

public enum CaffeineCacheEnum {
    coffee(30,100);
    private int maxSize;    // 最大数量
    private int ttl;       // 过期时间（秒）
    CaffeineCacheEnum(int ttl, int maxSize) {
        this.ttl = ttl;
        this.maxSize = maxSize;
    }
    public int getMaxSize() {
        return maxSize;
    }

    public int getTtl() {
        return ttl;
    }
}
