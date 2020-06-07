package com.kangpan.service.impl;

import com.kangpan.enums.CoffeeState;
import com.kangpan.mapper.OrderMapper;
import com.kangpan.model.Coffee;
import com.kangpan.model.Customer;
import com.kangpan.model.Order;
import com.kangpan.service.OrderService;
import com.kangpan.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RecordService recordService;

    @Override
    public List<Order> queryAll() {
        List<Order> list = orderMapper.queryAll();
        list.forEach(c-> {
            Customer customer = c.getCustomer();
            List<Coffee> items = customer.getItems();
            items.forEach(coffee -> {
                String coffeeDesc = CoffeeState.getStateDesc(coffee.getState());
                coffee.setStateDes(coffeeDesc);
            });
            orderMapper.save(c);
        });
        return list;
    }

    @Override
    public void save(Order order) {
        // 获取订单中的客户
        Customer customer = order.getCustomer();
        // 获取客户购买的咖啡
        List<Coffee> items = customer.getItems();
        items.forEach(c->{
        });
        // 保存记录中 咖啡 咖啡对应的订单
        orderMapper.save(order);
    }
}
