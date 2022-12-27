package com.southwind.feign;

import com.southwind.entity.Order;
import com.southwind.entity.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@FeignClient(value = "dingdan")
public interface OrderFeign {

    @PostMapping("/dingdan/save")
    public void save(@RequestBody Order order);

    @GetMapping("/dingdan/findAllByUid/{index}/{limit}/{uid}")
    public OrderVO findAllByUid(@PathVariable("index") int index, @PathVariable("limit") int limit, @PathVariable("uid") long uid);

    @GetMapping("/dingdan/findAllByMid/{mid}")
    public List<Order> findAllByMid(@PathVariable("mid") int mid);

    @PutMapping("/dingdan/updateState/{id}")
    public void updateState(@PathVariable("id") long id);

    @GetMapping("/dingdan/findAll/{index}/{limit}")
    public OrderVO findAll(@PathVariable("index") int index,@PathVariable("limit") int limit);









    @DeleteMapping("/order/deleteByMid/{mid}")
    public void deleteByMid(@PathVariable("mid") long mid);

    @DeleteMapping("/order/deleteByUid/{uid}")
    public void deleteByUid(@PathVariable("uid") long uid);

}
