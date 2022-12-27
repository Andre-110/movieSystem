package com.southwind.feign;

import com.southwind.entity.Movie;
import com.southwind.entity.MovieVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "movie")
public interface MovieFeign {

    @GetMapping("/movie/findAll/{index}/{limit}")
    public MovieVO findAll(@PathVariable("index") int index,@PathVariable("limit") int limit);

    @GetMapping("/movie/findById/{id}")
    public Movie findById(@PathVariable("id") long id);

    @PostMapping("/movie/save")
    public void save(@RequestBody Movie movie);

    @PutMapping("/movie/update")
    public void update(@RequestBody Movie movie);

    @DeleteMapping("/movie/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id);

    @GetMapping("/movie/count")
    public int count();

    @GetMapping("/movie/findByIdJson/{id}")
    public Map<String,Object> map(@PathVariable("id") int id);

}
