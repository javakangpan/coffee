package com.kangpan.controller;

import com.kangpan.model.Customer;
import com.kangpan.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "CustomerController", description = "客户API服务")
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/findCustomerByName")
    @ResponseBody
    @ApiOperation("根据名称查找客户信息")
    public Customer findCustomerByName(String customerName) {
        return customerService.findCustomerByName(customerName);
    }
}
