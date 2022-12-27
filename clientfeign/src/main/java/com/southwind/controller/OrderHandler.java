package com.southwind.controller;

import com.southwind.entity.*;
import com.southwind.feign.OrderFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/dingdan")
@Api(value = "/dingdan", description = "这是订单的接口文档")
public class OrderHandler {

    @Autowired
    private OrderFeign orderFeign;

    @ApiOperation(value = "新增一条订单", notes = "此处为注释 ")
    @GetMapping("/save/{mid}/{address}")
    public String save(@PathVariable("mid") long mid, @PathVariable("address") long address,HttpSession session){
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        order.setUser(user);

        Movie movie = new Movie();
//        Menu menu = new Menu();
//        menu.setId(mid);
        movie.setId((int) mid);

        order.setMovie(movie);
        order.setAddress((int) address);

        orderFeign.save(order);
        return "my_order";
    }

    @ApiOperation(value = "按用户ID查询订单", notes = "此处为注释 ")
    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page") int page, @RequestParam("limit") int limit,HttpSession session){
        User user = (User) session.getAttribute("user");
        int index = (page-1)*limit;
        return orderFeign.findAllByUid(index,limit, user.getId());
    }

    @ApiOperation(value = "按电影ID查询订单", notes = "此处为注释 ")
    @GetMapping("/findAllByMid/{mid}")
    @ResponseBody
    public List<Order> findAllByMid(@PathVariable("mid") int mid){
//        int index = (page-1)*limit;
        return orderFeign.findAllByMid(mid);
    }

    @ApiOperation(value = "查询所有订单的列表", notes = "此处为注释 ")
    @GetMapping("/findAll")
    @ResponseBody
    public OrderVO findAll(@RequestParam("page") int page,@RequestParam("limit") int limit){
        int index = (page-1)*limit;
        OrderVO orderVO = new OrderVO();
        orderVO = orderFeign.findAll(index,limit);
        return orderVO;
    }

    @ApiOperation(value = "更新订单的支付状态", notes = "此处为注释 ")
    @GetMapping("/updateState/{id}")
    public String updateState(@PathVariable("id") long id){
        orderFeign.updateState(id);
        return "redirect:/menu/redirect/order_handler";
    }
}
