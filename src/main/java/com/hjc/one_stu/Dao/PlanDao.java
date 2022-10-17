package com.hjc.one_stu.Dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author csz
 * @Date 2022/9/22 15:16
 */
@Data
@TableName("plan")
public class PlanDao {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "plan_time")
    private Date planTime;

    @TableField(value = "userId")
    private Integer userId;

    @TableField(value = "couserId")
    private String couserId;

    @TableField(value = "context")
    private String context;

    @TableField(value = "status")
    private String status;

    @TableField(value = "title")
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(exist = false)
    private Date endDate;
}
