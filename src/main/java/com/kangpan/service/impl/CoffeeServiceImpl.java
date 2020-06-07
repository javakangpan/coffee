
package com.kangpan.service.impl;

import com.kangpan.mapper.CoffeeMapper;
import com.kangpan.model.Coffee;
import com.kangpan.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Transactional
@Service
@Slf4j
public class CoffeeServiceImpl implements CoffeeService {

    @Autowired
    private CoffeeMapper coffeeMapper;

    @Override
    public List<Coffee> selectAll() {
        // 测试
        List<Coffee> list = coffeeMapper.selectAll();
        log.info("selectAll ==> {}",list );
        return list;
    }

    @Override
    public List<Coffee> select(Coffee coffee) {
        // 测试
        coffee = Coffee.builder().id(1).build();
        List<Coffee> list = coffeeMapper.select(coffee);
        log.info("select ==> {}",list );
        return list;
    }

    @Override
    public int insert(Coffee coffee) {
        // 测试
        coffee = Coffee.builder().name("测试数据-insert")
                .price(1).state(104).coffeeDesc("测试数据-insert").updatedBy("system")
                .createdBy("system").createTime(new Date()).updateTime(new Date()).build();
        log.info("insert: {}",coffee);
        return coffeeMapper.insert(coffee);
    }

    @Override
    public int update(Coffee coffee) {
        // 测试
        coffee = Coffee.builder().id(1).name("测试数据-update")
                .price(1).state(104).coffeeDesc("测试数据-update").updatedBy("system")
                .createdBy("system").createTime(new Date()).updateTime(new Date()).build();
        log.info("coffee: {}", coffee);
        return coffeeMapper.update(coffee);
    }

    @Override
    public int delete(long id) {
        // 测试
        return coffeeMapper.delete(12);
    }

    @Override
    public List<Coffee> batchSelect(List<Coffee> items) {
        // 测试
        items = new ArrayList<>();
        Coffee coffee1 = Coffee.builder().id(1).build();
        Coffee coffee2 = Coffee.builder().id(2).build();
        items.add(coffee1);
        items.add(coffee2);
        List<Coffee> list = coffeeMapper.batchSelect(items);
        log.info("batchSelect ==> {}",list );
        return list;
    }

    @Override
    public int batchInsert(List<Coffee> items) {
        // 测试
        items = new ArrayList<>();
        Coffee coffee1 = Coffee.builder()
                .name("测试数据-batchInsert")
                .price(1)
                .state(104)
                .coffeeDesc("测试数据-batchInsert")
                .build();
        Coffee coffee2 = Coffee.builder()
                .name("测试数据-batchInsert")
                .price(2)
                .state(104)
                .coffeeDesc("测试数据-batchInsert")
                .build();
        items.add(coffee1);
        items.add(coffee2);
        return coffeeMapper.batchInsert(items);
    }

    @Override
    public int batchDelete(long[] ids) {
        // 测试
        ids = new long[]{1,2};
        return coffeeMapper.batchDelete(ids);
    }

    @Override
    public int batchUpdate(List<Coffee> items) {
        // 测试
        items = new ArrayList<>();
        Coffee coffee1 = Coffee.builder()
                .id(1)
                .name("测试数据-batchInsert")
                .price(1)
                .state(104)
                .coffeeDesc("测试数据-batchInsert")
                .build();
        Coffee coffee2 = Coffee.builder()
                .id(2)
                .name("测试数据-batchInsert")
                .price(2)
                .state(104)
                .coffeeDesc("测试数据-batchInsert")
                .createdBy("测试数据")
                .updatedBy("测试数据")
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        items.add(coffee1);
        items.add(coffee2);
        return coffeeMapper.batchUpdate(items);
    }
}
