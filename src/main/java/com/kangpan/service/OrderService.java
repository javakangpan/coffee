package com.kangpan.service;

import com.kangpan.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> queryAll();
    void save(Order order);
}
