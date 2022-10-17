package com.hjc.one_stu.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjc.one_stu.Dao.Question;
import com.hjc.one_stu.Dao.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * @Author csz
 * @Date 2022/10/7 23:36
 */
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 随机查询一条数据
     * @return
     */
    @Select("select * from question where parentId=#{parentId} order by RAND() limit 1 ")
    List<Question> getRandQuestion(Question question);

    @Select("select  count(id) from record where userId = #{context} and questionId = #{id} and deleted = 0 limit 1; ")
    Integer getRecord(Question question);

    @Select("    select C.*,D.context from (\n" +
            "    select parentId,count(*) as errorSum from record A left join question B\n" +
            "    on A.questionId = B.id\n" +
            "    where A.deleted = 0 and userId = #{id}\n" +
            "    GROUP BY B.parentId\n" +
            "    ) C LEFT JOIN question D\n" +
            "    on c.parentId = D.`options`\n" +
            "    GROUP BY c.parentId")
    List<HashMap<String,String>> selectError(User user);

}
