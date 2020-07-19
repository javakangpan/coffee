package com.kangpan.task;

import com.kangpan.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
public class CoffeeTask {

    @Autowired
    private CoffeeService coffeeService;

    @Scheduled(cron = "0 14 23 * * ?")
    private void update() {
        coffeeService.update();
    }
    @Scheduled(cron = "0 50 23 * * ?")
    private void normal() {
        coffeeService.normal();
    }
}
