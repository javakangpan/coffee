package com.kangpan.service.impl;

import com.google.gson.Gson;
import com.kangpan.enums.CoffeeState;
import com.kangpan.mapper.OrderMapper;
import com.kangpan.model.Coffee;
import com.kangpan.model.Customer;
import com.kangpan.model.Order;
import com.kangpan.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

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
        });
        return list;
    }

    @Override
    public void save(Order order) {
        List<Coffee> items = new ArrayList<>();
        Coffee coffee1 = Coffee.builder().id(1).build();
        Coffee coffee2 = Coffee.builder().id(2).build();
        items.add(coffee1);
        items.add(coffee2);
        Customer customer = Customer.builder().id(1).items(items).build();
        order = Order.builder().customer(customer).state(201).build();
        Gson gson = new Gson();
        log.info(gson.toJson(order));
        orderMapper.save(order);
    }
}
