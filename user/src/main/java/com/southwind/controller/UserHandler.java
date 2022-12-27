package com.southwind.controller;

import com.southwind.entity.User;
import com.southwind.entity.UserVO;
import com.southwind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public UserVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        UserVO userVO = new UserVO();
        userVO.setCode(0);
        userVO.setMsg("");
        userVO.setCount(userRepository.count());
        return new UserVO(0,"",userRepository.count(),userRepository.findAll(index,limit));
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") long id){
        return userRepository.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody User user){
        Timestamp createTime = new Timestamp(new Date().getTime());
        user.setRegisterdate(createTime);
        userRepository.save(user);
    }

    @PutMapping("/update")
    public void update(@RequestBody User user){
        userRepository.update(user);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        userRepository.deleteById(id);
    }

    @GetMapping("/count")
    public int count(){
        return userRepository.count();
    }

//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/findAll/{index}/{limit}")
//    public List<User> findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
//        return userRepository.findAll(index,limit);
//    }
//    
//    @GetMapping("/findById/{id}")
//    public UserVO findById(@PathVariable("id") long id){
//        UserVO userVO = new UserVO();
//        userVO.setMsg("");
//        userVO.setCount(1);
//        userVO.setData(userRepository.findById(id));
//        return userVO;
//    }
//    @GetMapping("/findByAdminId/{id}")
//    public UserVO findAdminById(@PathVariable("id") long id){
//        UserVO userVO = new UserVO();
//        userVO.setMsg("");
//        userVO.setCount(1);
//        userVO.setData(userRepository.findAdminById(id));
//        return userVO;
//    }
//
//    @GetMapping("/count")
//    public int count(){
//        return userRepository.count();
//    }
//
//
//    @PostMapping("/save")
//    public void save(@RequestBody User user){
//        user.setRegisterdate(new Date());
//        userRepository.save(user);
//    }
//
//    @PutMapping("/update")
//    public void update(@RequestBody User user){
//        userRepository.update(user);
//    }
//
//    @DeleteMapping("/deleteById/{id}")
//    public void deleteById(@PathVariable("id") long id){
//        userRepository.deleteById(id);
//    }
//
//    @PutMapping("/update_password")
//    public void update_password(@RequestBody User user){
//        userRepository.update_password(user);
//    }
}
