package com.hjc.one_stu.Controller;

import com.hjc.one_stu.Dao.PlanDao;
import com.hjc.one_stu.Service.Interfaces.PlanService;
import com.hjc.one_stu.Utils.BaseResponseData.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author csz
 * @Date 2022/9/22 15:23
 */

@RequestMapping("/Plan")
@RestController
public class PlanController {

    @Resource
    private PlanService service;

    // /Plan/addPlan 新增计划
    @RequestMapping("/addPlan")
    public ResponseData addPlan(PlanDao planDao){
        return service.addPlan(planDao);
    }

    // /Plan/ListPlan 计划列表，传了时间就是查询时间的
    @RequestMapping("/ListPlan")
    public ResponseData ListPlan(PlanDao planDao){
        return service.ListPlan(planDao);
    }

    // /Plan/getByIdPlan 通过id获取计划详情
    @RequestMapping("/getByIdPlan")
    public ResponseData getByIdPlan(PlanDao planDao){
        return service.getByIdPlan(planDao);
    }

    // /Plan/updatePlanStatus 更改计划状态 0未完成，1已经完成，2删除 传参需要 id,status
    @RequestMapping("/updatePlanStatus")
    public ResponseData updatePlanStatus(PlanDao planDao){
        return service.updatePlanStatus(planDao);
    }





}
