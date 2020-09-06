package com.kangpan.service.impl;

import com.kangpan.mapper.CustomerMapper;
import com.kangpan.model.Customer;
import com.kangpan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    public CustomerMapper customerMapper;

    @Override
    public Customer findCustomerByName(String customerName) {
        return customerMapper.findCustomerByName(customerName);
    }
}
