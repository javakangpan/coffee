package com.kangpan.mapper;

import com.kangpan.model.Order;

import java.util.List;

public interface OrderMapper {
    List<Order> queryAll();
    void save(Order order);
}
