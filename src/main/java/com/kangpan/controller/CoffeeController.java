package com.kangpan.controller;

import com.kangpan.common.Assert;
import com.kangpan.common.ResponseEntity;
import com.kangpan.model.Coffee;
import com.kangpan.service.CoffeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/coffee")
@Api(tags = "CoffeeController", description = "咖啡API服务")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    /**
     * produces 指定返回的内容类型
     *
     * @return List<Coffee>
     */
    @GetMapping(path = "/selectAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation("查询所有咖啡")
    public List<Coffee> selectAll() {
        return coffeeService.selectAll();
    }

    /**
     * consumes 指定请求的提交内容类型(Content-Type)
     *
     * @param coffee
     * @return int
     */
    @PostMapping(path = "/insert",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation("新增咖啡")
    public ResponseEntity insert(@RequestBody @Valid Coffee coffee, BindingResult bindingResult) {
        Assert.validation(bindingResult.hasErrors(), bindingResult);
        coffeeService.insert(coffee);
        return ResponseEntity.builder().data(coffee)
                .time(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("insert is ok")
                .build();
    }

}
