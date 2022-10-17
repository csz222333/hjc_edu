package com.hjc.one_stu.Service.Interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjc.one_stu.Dao.PlanDao;
import com.hjc.one_stu.Dao.Question;
import com.hjc.one_stu.Utils.BaseResponseData.ResponseData;


/**
 * @Author csz
 * @Date 2022/9/22 15:18
 */
public interface PlanService extends IService<PlanDao> {

    ResponseData addPlan(PlanDao planDao);

    ResponseData ListPlan(PlanDao planDao);

    ResponseData getByIdPlan(PlanDao planDao);

    ResponseData updatePlanStatus(PlanDao planDao);
}
