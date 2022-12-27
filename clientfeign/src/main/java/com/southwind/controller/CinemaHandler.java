package com.southwind.controller;

import com.southwind.entity.Cinema;
import com.southwind.entity.CinemaVO;
import com.southwind.entity.MovieVO;
import com.southwind.feign.CinemaFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
//注意RestController与Controller区别
@RequestMapping("/cinema")
@Api(value = "/cinema", description = "这是电影的接口文档")
public class CinemaHandler {

    @Autowired
    private CinemaFeign cinemaFeign;

    @ApiOperation(value = "查询所有电影院的列表", notes = "此处为注释 ")
    @GetMapping("/findAll")
    @ResponseBody
    public CinemaVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return cinemaFeign.findAll(index,limit);
    }


    @ApiOperation(value = "按电影院ID查询", notes = "此处为注释 ")
    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cinema_update");
        modelAndView.addObject("cinema",cinemaFeign.findById(id));
        return modelAndView;
    }

    @ApiOperation(value = "新增电影院", notes = "此处为注释 ")
    @PostMapping("/save")
    public String save(Cinema cinema){
        cinemaFeign.save(cinema);
        return "redirect:/menu/redirect/cinema_manage";
    }

    @ApiOperation(value = "更新电影院信息", notes = "此处为注释 ")
    //注意页面客户端页面始终要跳转
    @PostMapping("/update")
    public String update(Cinema cinema){
        cinemaFeign.update(cinema);
        return "redirect:/menu/redirect/cinema_manage";
    }

    @ApiOperation(value = "删除电影院信息", notes = "此处为注释 ")
    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        //orderFeign.deleteByMid(id);
        cinemaFeign.deleteById(id);
        return "redirect:/menu/redirect/cinema_manage";
    }
}
