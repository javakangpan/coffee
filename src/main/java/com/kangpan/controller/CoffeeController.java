package com.kangpan.controller;

import com.kangpan.model.Coffee;
import com.kangpan.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @ResponseBody
    @GetMapping(path = "/test")
    public String test() {
        Coffee coffee = new Coffee();
        List<Coffee> items = new ArrayList<>();
        long[] ids = new long[10];
        coffeeService.selectAll();
        coffeeService.insert(coffee);
        coffeeService.update(coffee);
        coffeeService.delete(1);
        coffeeService.batchSelect(items);
        coffeeService.batchInsert(items);
        coffeeService.batchUpdate(items);
        coffeeService.batchDelete(ids);
        return "test";
    }
}
