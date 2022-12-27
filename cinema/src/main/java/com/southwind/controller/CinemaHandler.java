package com.southwind.controller;

import com.southwind.entity.Cinema;
import com.southwind.entity.CinemaVO;
import com.southwind.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaHandler {

    @Autowired
    private CinemaRepository cinemaRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public CinemaVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        CinemaVO cinemaVO = new CinemaVO();
        cinemaVO.setCode(0);
        cinemaVO.setMsg("");
        cinemaVO.setCount(cinemaRepository.count());
        return new CinemaVO(0,"",cinemaRepository.count(),cinemaRepository.findAll(index,limit));
    }

    @GetMapping("/findById/{id}")
    public Cinema findById(@PathVariable("id") long id){
        return cinemaRepository.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Cinema cinema){
        Timestamp createTime = new Timestamp(new Date().getTime());
        cinema.setRegisterDate(createTime);
        cinemaRepository.save(cinema);
    }

    @PutMapping("/update")
    public void update(@RequestBody Cinema cinema){
        cinemaRepository.update(cinema);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        cinemaRepository.deleteById(id);
    }

    @GetMapping("/count")
    public int count(){
        return cinemaRepository.count();
    }


}
