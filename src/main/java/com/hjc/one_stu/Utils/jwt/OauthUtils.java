package com.hjc.one_stu.Utils.jwt;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hjc.one_stu.Dao.User;
import com.hjc.one_stu.Dao.UserVO.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author csz
 * @Date 2022/10/7 01:07
 */
@Slf4j
public class OauthUtils {
    public static UserVo getCurrentUser(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = sra.getRequest();

        String  token= request.getHeader("jwt-token");
        token = PassWorldUtils.decrypContext(token);
        return tokenToUserVo(token);
    }

    public static UserVo tokenToUserVo(String token){

        JSONObject entries = JSONUtil.parseObj(token);

        Long ex_time = entries.getLong("ex_time");

        User user = entries.getBean("user", User.class);


        UserVo userVo = new UserVo(user);
        userVo.setEx_time(ex_time);

        return userVo;
    }
}
