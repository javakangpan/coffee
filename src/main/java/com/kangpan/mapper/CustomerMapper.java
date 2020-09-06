package com.kangpan.mapper;

import com.kangpan.model.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    Customer findCustomerByName(@Param("customerName") String customerName);
}
