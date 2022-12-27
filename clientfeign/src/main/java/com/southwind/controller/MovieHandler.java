package com.southwind.controller;

import com.southwind.entity.Movie;
import com.southwind.entity.MovieVO;
import com.southwind.feign.MovieFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
//注意RestController与Controller区别
@RequestMapping("/movie")
@Api(value = "/movie", description = "这是电影院的接口文档")
public class MovieHandler {

    @Autowired
    private MovieFeign movieFeign;

    @ApiOperation(value = "查询电影信息列表", notes = "此处为注释 ")
    @GetMapping("/findAll")
    @ResponseBody
    public MovieVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return movieFeign.findAll(index,limit);
    }

    @ApiOperation(value = "按ID查询电影信息", notes = "此处为注释 ")
    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("movie_update");
        modelAndView.addObject("movie",movieFeign.findById(id));
        return modelAndView;
    }

    @ApiOperation(value = "新增一条电影信息", notes = "此处为注释 ")
    @PostMapping("/save")
    public String save(Movie movie){
        movieFeign.save(movie);
        return "redirect:/menu/redirect/movie_manage";
    }


    //注意页面客户端页面始终要跳转
    @ApiOperation(value = "更新电影信息", notes = "此处为注释 ")
    @PostMapping("/update")
    public String update(Movie movie){
        movieFeign.update(movie);
        return "redirect:/menu/redirect/movie_manage";
    }

    @ApiOperation(value = "按ID删除电影信息", notes = "此处为注释 ")
    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        //orderFeign.deleteByMid(id);
        movieFeign.deleteById(id);
        return "redirect:/menu/redirect/movie_manage";
    }

    @ApiOperation(value = "按ID获取电影的JSON数据", notes = "此处为注释 ")
    @GetMapping("/findByIdJson/{id}")
    @ResponseBody
    public Map<String,Object> map(@PathVariable int id){
        return movieFeign.map(id);
    }
}
