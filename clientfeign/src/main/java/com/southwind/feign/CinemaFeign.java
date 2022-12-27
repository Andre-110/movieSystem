package com.southwind.feign;

import com.southwind.entity.OrderVO;
import com.southwind.entity.Cinema;
import com.southwind.entity.CinemaVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "cinema")
public interface CinemaFeign {

    @GetMapping("/cinema/findAll/{index}/{limit}")
    public CinemaVO findAll(@PathVariable("index") int index,@PathVariable("limit") int limit);

    @GetMapping("/cinema/findById/{id}")
    public Cinema findById(@PathVariable("id") long id);

    @PostMapping("/cinema/save")
    public void save(@RequestBody Cinema cinema);

    @PutMapping("/cinema/update")
    public void update(@RequestBody Cinema cinema);
    @DeleteMapping("/cinema/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id);

    @GetMapping("/cinema/count")
    public int count();


}
