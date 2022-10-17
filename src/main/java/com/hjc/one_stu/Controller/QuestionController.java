package com.hjc.one_stu.Controller;

import com.hjc.one_stu.Dao.Question;
import com.hjc.one_stu.Service.Interfaces.QuestionServer;
import com.hjc.one_stu.Utils.BaseResponseData.ResponseData;
import com.hjc.one_stu.Utils.Page.page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author csz
 * @Date 2022/9/22 19:55
 */
@RequestMapping("/Question")
@RestController
public class QuestionController {

    @Autowired
    private QuestionServer server;

    @PostMapping("/Catalog")
    public ResponseData CatCatalog(page page){
        return server.CatCatalog(page);
    }

    @PostMapping("/getQuestion")
    public ResponseData getQuestion(Question question){

        return server.getQuestion(question);
    }

    @PostMapping("/saveErrorQuestion")
    public ResponseData saveErrorQuestion(Question question){
        server.saveErrorQuestion(question);
        return ResponseData.success();
    }

    @PostMapping("/getRecordErrorQuestion")
    public ResponseData getRecordErrorQuestion(page pages){

        return server.getRecordErrorQuestion(pages);
    }

    /**
     * 删除错题记录
     * @param question
     * @return
     */
    @PostMapping("/deleteErrorQuestion")
    public ResponseData deleteErrorQuestion(Question question){

        return server.deleteErrorQuestion(question);
    }

    /**
     * 通过题目id 获取题目信息 /Question/getQuestionById
     * @param question
     * @return
     */
    @PostMapping("/getQuestionById")
    public ResponseData getQuestionById(Question question){

        return server.getQuestionById(question);
    }

    @PostMapping("/getErrorQS")
    public ResponseData getErrorQS(){

        return server.getErrorQS();
    }



}
