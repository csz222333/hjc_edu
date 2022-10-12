package com.hjc.one_stu.Dao.UserVO;

import cn.hutool.core.date.DateUtil;
import com.hjc.one_stu.Dao.User;
import lombok.Builder;

import java.io.Serializable;

/**
 * @Author csz
 * @Date 2022/10/7 00:34
 */
public class UserVo implements Serializable  {
    private User user;

    /**
     * 过期时间，作为视图token
     */
    private long ex_time;

    public UserVo(User user){
        this.user = user;
        this.user.setPassword(null);
        //设置过期时间
        this.ex_time = DateUtil.date().getTime()+72*60*60*1000L;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getEx_time() {
        return ex_time;
    }

    public void setEx_time(long ex_time) {
        this.ex_time = ex_time;
    }

    /**
     * 获取token是否过期 true过期，false未过期
     * @return
     */
    public Boolean getExFlag(){
       return DateUtil.date().getTime()>ex_time;
    }
}
