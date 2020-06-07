package com.kangpan.service.impl;

import com.kangpan.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
@Slf4j
public class RecordServiceImpl implements RecordService {
    @Override
    public void insertRecord(long coffeeId, List<Long> orderId) {

    }
}
