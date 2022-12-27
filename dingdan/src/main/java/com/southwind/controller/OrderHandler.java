package com.southwind.controller;

import com.southwind.entity.Order;
import com.southwind.entity.OrderVO;
import com.southwind.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dingdan")
public class OrderHandler {

    @Autowired
    private OrderRepository orderRepository;


    @PostMapping("/save")
    public void save(@RequestBody Order order){

        order.setAddtime(new Timestamp(new Date().getTime()));
        orderRepository.save(order);
    }


    @GetMapping("/findAllByUid/{index}/{limit}/{uid}")
    public OrderVO findAllByUid(@PathVariable("index") int index,@PathVariable("limit") int limit,@PathVariable("uid") long uid){
        OrderVO orderVO = new OrderVO();
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.countByUid(uid));
        orderVO.setData(orderRepository.findAllByUid(index,limit,uid));
        return orderVO;
    }

    @GetMapping("/findAllByMid/{mid}")
    public List<Order> findAllByMid(@PathVariable("mid") long mid){
//        OrderVO orderVO = new OrderVO();
//        orderVO.setMsg("");
//        orderVO.setCount(orderRepository.countByMid(mid));
//        orderVO.setData(orderRepository.findAllByMid(mid));
//        return orderVO;
        return orderRepository.findAllByMid(mid);
    }

    @GetMapping("/countByUid/{uid}")
    public int countByUid(@PathVariable("uid") int uid){
        return orderRepository.countByUid(uid);
    }




    @GetMapping("/findAll/{index}/{limit}")
    public OrderVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
//        return orderRepository.findAll(index,limit);
        OrderVO orderVO = new OrderVO();
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.count());
        orderVO.setData(orderRepository.findAll(index,limit));
        return orderVO;
    }

//    @PutMapping("/update/{id}")
//    public void update(@PathVariable("id")long id){
//        orderRepository.updateState(id);
//    }


}
