package com.hjc.one_stu.Controller;

import com.hjc.one_stu.Dao.User;
import com.hjc.one_stu.Service.Interfaces.UmsAdminService;
import com.hjc.one_stu.Utils.BaseResponseData.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author csz
 * @Date 2022/9/22 14:56
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UmsAdminService service;

    @PostMapping("/login")
    public ResponseData login(User user){
        return service.loginUser(user);
    }

    @PostMapping("/register")
    public ResponseData registerUser(User user){

        return service.registerUser(user);
    }

    @PostMapping("/findUser")
    public ResponseData findUserByUsername(User user){

       return service.findUserByUsername(user);
    }

    @PostMapping("/update")
    public ResponseData updateUser(User user){
        return service.updataUser(user);
    }




}
