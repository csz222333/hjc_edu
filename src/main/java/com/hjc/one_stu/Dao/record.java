package com.hjc.one_stu.Dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author csz
 * @Date 2022/10/10 23:27
 */
@TableName("record")
@Data
public class record {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer Id;

    @TableField(value = "userId")
    private Integer UserId;

    @TableField(value = "questionId")
    private Integer questionId;

    @TableField(value = "situation")
    private String situation;

    @TableField(value = "createTime")
    private LocalDateTime createTime;

    @TableField(value = "deleted")
    private String deleted;
}
