package com.southwind.controller;

import com.southwind.entity.Movie;
import com.southwind.entity.MovieVO;
import com.southwind.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieHandler {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public MovieVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        MovieVO movieVO = new MovieVO();
        movieVO.setCode(0);
        movieVO.setMsg("");
        movieVO.setCount(movieRepository.count());
        return new MovieVO(0,"",movieRepository.count(),movieRepository.findAll(index,limit));
    }

    @GetMapping("/findById/{id}")
    public Movie findById(@PathVariable("id") long id){
        Movie movie = movieRepository.findById(id);
        return movie;
    }

    @PostMapping("/save")
    public void save(@RequestBody Movie movie){
        Timestamp createTime = new Timestamp(new Date().getTime());
        movie.setRegisterDate(createTime);
        movieRepository.save(movie);
    }

    @PutMapping("/update")
    public void update(@RequestBody Movie movie){
        movieRepository.update(movie);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        movieRepository.deleteById(id);
    }

    @GetMapping("/count")
    public int count(){
        return movieRepository.count();
    }

    @Autowired
    JdbcTemplate jdbcTemplate;


    @ResponseBody
    @GetMapping("/findByIdJson/{id}")
    public Map<String, Object> map(@PathVariable int id) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_movie");

        Map<String,Object> temp;
        temp = list.get(id);
//        for(int i = 0;i < list.size();i++){
//            Map<String,Object> li= list.get(i);
//            Iterator iterator = li.keySet().iterator();
////            if(li.get("id")==id){
////                temp = li;
////            }
//            while(iterator.hasNext()){
//                String key = (String) iterator.next();
//                if((int)li.get(key)==id){
//                    temp = li;
//                }
//            }
//        }

        return temp;
    }

}


