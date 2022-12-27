package com.southwind.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author haotonghui <haotonghui@kuaishou.com>
 * Created on 2020-12-15
 */
@RestController
@RequestMapping("/swagger/test")
@Api(tags = "SwaggerTest", description = "swagger接口调用的测试")
public class SwaggerTestController {


    @GetMapping("getTest")
    @ApiOperation(value = "获取信息")
    public String getTest() {
        return "信息";
    }

    @PostMapping("postTest")
    @ApiOperation(value = "新增信息")
    public String postTest() {
        return "新增信息";
    }


}
