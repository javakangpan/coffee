package com.kangpan.service;

import java.util.List;

public interface RecordService {
    void insertRecord(long coffeeId, List<Long> orderId);
}
