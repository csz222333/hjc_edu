package com.hjc.one_stu.Service.Impl;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.one_stu.Dao.User;
import com.hjc.one_stu.Dao.UserVO.UserVo;
import com.hjc.one_stu.Mapper.UmsAdminMapper;
import com.hjc.one_stu.Service.Interfaces.UmsAdminService;
import com.hjc.one_stu.Utils.BaseResponseData.ResponseData;
import com.hjc.one_stu.Utils.jwt.OauthUtils;
import com.hjc.one_stu.Utils.jwt.PassWorldUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.util.HashMap;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author csz
 * @since 2021-11-23
 */
@Slf4j
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, User> implements UmsAdminService {


    @Override
    public ResponseData findUserByUsername(User user) {
        User user1 = baseMapper.selectById(user.getId());
        return ResponseData.success(200,"请求成功",user1);
    }

    @Override
    public ResponseData registerUser(User user) {

        String password = PassWorldUtils.cryContext(user.getPassword());
        user.setPassword(password);
        save(user);

        return ResponseData.success();
    }

    @Override
    public ResponseData loginUser(User user) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //对比手机号码，从Mysql数据库得到用户对象
        queryWrapper.lambda().
                eq(User::getPhone,user.getPhone());

        User user1 = baseMapper.selectOne(queryWrapper);

        //判断密码是否相等
        try{
            if(user1.getPassword().equals(PassWorldUtils.cryContext(user.getPassword()))){

                UserVo vo = new UserVo(user1);
                //返回token
                JSONObject json = JSONUtil.parseObj(vo, false);
                String s = json.toStringPretty();
                String token = PassWorldUtils.cryContext(s);
                HashMap map = new HashMap<>();
                map.put("token",token);
                map.put("data",vo.getUser());
                return ResponseData.success(200,"请求成功",map);
            }

        }catch (Exception e){
            log.error(e.getMessage());
        }


        return ResponseData.error(503,"请求失败，密码错误");
    }

    @Override
    public ResponseData updataUser(User user) {

        if(StrUtil.isNotEmpty(user.getPassword())){
            String password = PassWorldUtils.cryContext(user.getPassword());
            user.setPassword(password);
        }else {
            User ls = baseMapper.selectById(OauthUtils.getCurrentUser().getUser().getId());
            user.setPassword(ls.getPassword());
        }

        baseMapper.updateById(user);

        return ResponseData.success();
    }


}
