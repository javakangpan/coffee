
package com.kangpan.service.impl;

import com.kangpan.common.PricePracel;
import com.kangpan.common.Wrapping;
import com.kangpan.enums.CoffeeState;
import com.kangpan.mapper.CoffeeMapper;
import com.kangpan.model.Coffee;
import com.kangpan.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
@Slf4j
public class CoffeeServiceImpl implements CoffeeService {

    @Autowired
    private CoffeeMapper coffeeMapper;

    @Override
    @Cacheable(cacheNames = "coffee")
    public List<Coffee> selectAll() {
        List<Coffee> items = coffeeMapper.selectAll();
        items.forEach(item -> item.setStateDes(CoffeeState.getStateDesc(item.getState())));
        return items;
    }

    @Override
    public List<Coffee> select(Coffee coffee) {
        return coffeeMapper.select(coffee);
    }

    @Override
    public int insert(Coffee coffee) {
        return coffeeMapper.insert(coffee);
    }

    @Override
    public int update(Coffee coffee) {
        return coffeeMapper.update(coffee);
    }

    @Override
    public int delete(long id) {
        return coffeeMapper.delete(id);
    }
    @Override
    public List<Coffee> batchSelect(List<Coffee> items) {
        return coffeeMapper.batchSelect(items);
    }

    @Override
    public int batchInsert(List<Coffee> items) {
        return coffeeMapper.batchInsert(items);
    }

    @Override
    public int batchDelete(long[] ids) {
        return coffeeMapper.batchDelete(ids);
    }

    @Override
    public int batchUpdate(List<Coffee> items) {
        return coffeeMapper.batchUpdate(items);
    }

    /**
     * 晚上 8:30 开始所有商品打9折 定时任务执行
     * @return int
     */
    @Override
    public int update() {
        List<Coffee> items = coffeeMapper.selectAll();
        PricePracel pricePracel = new PricePracel();
        items = items.stream().filter(item -> item.getState() == 104).collect(Collectors.toList());
        items.forEach(item -> {
            Wrapping wrapping = pricePracel.discount(item.getPrice(), 104);
            item.setState(105);
            item.setPrice(wrapping.value());
        });
        return coffeeMapper.batchUpdate(items);
    }

    /**
     * 晚上 9:00 恢复原价 定时任务执行
     * @return int
     */
    @Override
    public int normal() {
        List<Coffee> items = coffeeMapper.selectAll();
        PricePracel pricePracel = new PricePracel();
        items = items.stream().filter(item -> item.getState() == 105).collect(Collectors.toList());
        items.forEach(item -> {
            Wrapping wrapping = pricePracel.discount(item.getPrice(), 105);
            item.setPrice(wrapping.value());
            item.setState(104);
        });
        return coffeeMapper.batchUpdate(items);
    }
}
