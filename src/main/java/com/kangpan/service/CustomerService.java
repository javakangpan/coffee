package com.kangpan.service;

import com.kangpan.model.Customer;

public interface CustomerService {
    Customer findCustomerByName(String customerName);
}
