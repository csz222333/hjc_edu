package com.hjc.one_stu.Dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author csz
 * @Date 2022/9/22 15:32
 */
@Data
@TableName("question")
public class Question implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "context")
    private String context;

    @TableField(value = "parentId")
    private String parentId;

    @TableField(value = "options")
    private String options;

    @TableField(value = "answer")
    private String answer;

    @TableField(value = "resolving")
    private String resolving;


}
