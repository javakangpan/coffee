package com.kangpan.controller;

import com.kangpan.annotation.Limit;
import com.kangpan.common.ResponseEntity;
import com.kangpan.model.Order;
import com.kangpan.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order")
@Api(tags = "OrderController", description = "订单API服务")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    @ResponseBody
    @ApiOperation("查询所有订单")
    public List<Order> queryAll() {
        return orderService.queryAll();
    }

    /**
     * 限制购买 coffee 次数
     * @return ResponseEntity
     */
    @PostMapping("/limitBuy")
    @Limit(key = "limitBuy", count = 10, period = 60)
    @ApiOperation("购买咖啡")
    @ResponseBody
    public ResponseEntity limitBuy(Order order) {
        orderService.save(order);
        return ResponseEntity.builder().message("购买成功").build();
    }
}
