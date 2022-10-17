package com.hjc.one_stu.Service.Impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.one_stu.Dao.PlanDao;
import com.hjc.one_stu.Dao.User;
import com.hjc.one_stu.Mapper.PlanMapper;
import com.hjc.one_stu.Service.Interfaces.PlanService;
import com.hjc.one_stu.Utils.BaseResponseData.ResponseData;
import com.hjc.one_stu.Utils.jwt.OauthUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author csz
 * @Date 2022/9/22 15:22
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, PlanDao> implements PlanService {

    @Override
    public ResponseData addPlan(PlanDao planDao) {
        planDao.setUserId(OauthUtils.getCurrentUser().getUser().getId().intValue());
        save(planDao);

        return ResponseData.success();
    }

    @Override
    public ResponseData ListPlan(PlanDao planDao) {

        User user = OauthUtils.getCurrentUser().getUser();

        QueryWrapper<PlanDao> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PlanDao::getUserId,user.getId()).eq(PlanDao::getStatus,"0").last("limit 9");

        if(planDao.getPlanTime()!=null &&planDao.getEndDate()!=null){
           queryWrapper.lambda().ge(PlanDao::getPlanTime,planDao.getPlanTime())
                   .le(PlanDao::getPlanTime,planDao.getEndDate());
        }


        List<PlanDao> planDaos = baseMapper.selectList(queryWrapper);

        return ResponseData.success(planDaos);
    }

    @Override
    public ResponseData getByIdPlan(PlanDao planDao) {

        PlanDao planDaod = baseMapper.selectById(planDao.getId());

        return ResponseData.success(planDaod);
    }

    @Override
    public ResponseData updatePlanStatus(PlanDao planDao) {

        boolean b = updateById(planDao);


        return ResponseData.success();
    }
}
