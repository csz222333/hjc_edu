package com.hjc.one_stu.Service.Interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjc.one_stu.Dao.Question;
import com.hjc.one_stu.Utils.BaseResponseData.ResponseData;
import com.hjc.one_stu.Utils.Page.page;

import java.util.List;

/**
 * @Author csz
 * @Date 2022/10/7 23:07
 */
public interface QuestionServer extends IService<Question> {

    ResponseData CatCatalog(page pages);

    ResponseData getQuestion(Question question);

    ResponseData saveErrorQuestion(Question question);

    ResponseData getRecordErrorQuestion(page pages);

    ResponseData deleteErrorQuestion(Question question);

    ResponseData getQuestionById(Question question);

    ResponseData getErrorQS();
}
