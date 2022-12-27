package com.southwind.controller;

import com.southwind.entity.Account;
import com.southwind.entity.Admin;
import com.southwind.entity.User;
import com.southwind.feign.AccountFeign;
import com.southwind.utils.ReflectUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
@Api(value = "/account", description = " 这是账户的接口文档")
public class AccountHandler {

    @Autowired
    private AccountFeign accountFeign;


    @ApiOperation(value = "登录", notes = "此处为注释 ")
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session) {
        Object object = accountFeign.login(username, password, type);
        LinkedHashMap<String, Object> hashMap = (LinkedHashMap) object;

        String result = null;
        String idStr = null;
        long id = 0L;

        if (object == null) {
            result = "login";
        } else {
            switch (type) {
                case "user":
                    User user = new User();
                    idStr = hashMap.get("id") + "";
                    id = Long.parseLong(idStr);
                    user.setId(id);
                    String nickname1 = (String) hashMap.get("nickname");
                    user.setNickname(nickname1);
                    session.setAttribute("user", user);
                    result = "index";
                    break;
                case "admin":
                    Admin admin = new Admin();
                    idStr = hashMap.get("id")+"";
                    id = Long.parseLong(idStr);
                    String nickname2 = (String) hashMap.get("nickname");
                    admin.setId(id);
                    admin.setNickname(nickname2);
                    session.setAttribute("admin", admin);
                    result = "main";
                    break;
            }
        }

        return result;
    }

    @ApiOperation(value = "退出", notes = "此处为注释 ")
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }


}

//        Account account = accountFeign.login(username,password,type);
//        String target = null;
//        if(account == null){
//            target = "login";
//        }else{
//            switch (type){
//                case "user":
//                    User user = convertUser(account);
//                    session.setAttribute("user",user);
//                    target = "redirect:/account/redirect/index";
//                    break;
//                case "admin":
//                    Admin admin = convertAdmin(account);
//                    session.setAttribute("admin",admin);
//                    target = "redirect:/account/redirect/main";
//                    break;
//            }
//        }
//        return target;
//    }

//    @GetMapping("/logout")
//    public String logout(HttpSession session){
//        session.invalidate();
//        return "login";
//    }
//
//    @RequestMapping("/redirect/{target}")
//    public String redirect(@PathVariable("target") String target){
//        return target;
//    }
//
//    private User convertUser(Account account){
//        User user = new User();
//        user.setUsername(ReflectUtils.getFieldValue(account,"username")+"");
//        user.setPassword(ReflectUtils.getFieldValue(account,"password")+"");
//        user.setGender(ReflectUtils.getFieldValue(account,"gender")+"");
//        user.setId((long)(ReflectUtils.getFieldValue(account,"id")));
//        user.setNickname(ReflectUtils.getFieldValue(account,"nickname")+"");
//        user.setRegisterdate((Date)(ReflectUtils.getFieldValue(account,"registerdate")));
//        user.setTelephone(ReflectUtils.getFieldValue(account,"telephone")+"");
//        return user;
//    }
//
//    private Admin convertAdmin(Account account){
//        Admin admin = new Admin();
//        admin.setUsername(ReflectUtils.getFieldValue(account,"username")+"");
//        admin.setPassword(ReflectUtils.getFieldValue(account,"password")+"");
//        admin.setId((long)(ReflectUtils.getFieldValue(account,"id")));
//        return admin;
//    }
//}

