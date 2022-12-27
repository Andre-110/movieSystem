package com.southwind.controller;

import com.southwind.entity.*;
import com.southwind.feign.OrderFeign;
import com.southwind.feign.UserFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
@Api(value = "/user", description = "这是用户的接口文档")
public class UserHandler {

    @Autowired
    private UserFeign userFeign;
    @Autowired
    private OrderFeign orderFeign;

    @ApiOperation(value = "查询所有用户的列表", notes = "此处为注释 ")
    @GetMapping("/findAll")
    @ResponseBody
    public UserVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return userFeign.findAll(index,limit);
    }


    @ApiOperation(value = "按ID查询用户订单", notes = "此处为注释 ")
    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page") int page, @RequestParam("limit") int limit,HttpSession session){
        User user = (User) session.getAttribute("user");
        int index = (page-1)*limit;
        OrderVO orderVO = orderFeign.findAllByUid(index,limit, user.getId());
        return orderVO;
    }

    @ApiOperation(value = "按ID查询用户", notes = "此处为注释 ")
    @GetMapping("/findById")
    @ResponseBody
    public ModelAndView findById(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User)session.getAttribute("user");
        modelAndView.addObject("user",userFeign.findById(user.getId()));
        return modelAndView;
    }

    @ApiOperation(value = "按ID查询管理员", notes = "此处为注释 ")
    @GetMapping("/findAdminById")
    @ResponseBody
    public ModelAndView findByAdminId(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User)session.getAttribute("user");
        modelAndView.addObject("user",userFeign.findAdminById(user.getId()));
        return modelAndView;
    }
    @ApiOperation(value = "统计用户个数", notes = "此处为注释 ")
    @GetMapping("/count")
    public int count(){
        return userFeign.count();
    }

    @ApiOperation(value = "新增一位用户", notes = "此处为注释 ")
    @PostMapping("/save")
    public String save(User user){
        user.setRegisterdate(new Date());
        userFeign.save(user);
        return "redirect:/menu/redirect/user_manage";
    }
    @ApiOperation(value = "更新用户信息", notes = "此处为注释 ")
    @PutMapping("/update")
    public void update(@RequestBody User user){
        userFeign.update(user);
    }

    @ApiOperation(value = "按ID删除用户信息", notes = "此处为注释 ")
    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        userFeign.deleteById(id);
        return "redirect:/user/redirect/user_manage";
    }
}
