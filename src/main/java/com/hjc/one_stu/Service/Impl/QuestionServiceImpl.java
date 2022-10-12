package com.hjc.one_stu.Service.Impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.one_stu.Dao.Question;
import com.hjc.one_stu.Dao.UserVO.UserVo;
import com.hjc.one_stu.Dao.record;
import com.hjc.one_stu.Mapper.QuestionMapper;
import com.hjc.one_stu.Service.Interfaces.QuestionServer;
import com.hjc.one_stu.Service.Interfaces.RecordServer;
import com.hjc.one_stu.Utils.BaseResponseData.ResponseData;
import com.hjc.one_stu.Utils.Page.page;
import com.hjc.one_stu.Utils.jwt.OauthUtils;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author csz
 * @Date 2022/9/22 15:35
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionServer {

    @Resource
    private RecordServer recordServer;

    @Override
    public ResponseData CatCatalog(page pages) {

        Page<Question> page =new Page<>(pages.getCurrent(),pages.getSize());
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Question::getParentId,"0");

        Page<Question> data = baseMapper.selectPage(page, queryWrapper);

        return ResponseData.success(data);
    }

    @Override
    public ResponseData getQuestion(Question question) {
        List<Question> randQuestion = baseMapper.getRandQuestion(question);
        ArrayList<Question> adds = new ArrayList<>();
        for (int i = 0; i < randQuestion.size(); i++) {
            Question question1 = randQuestion.get(i);
            String options = question1.getOptions();
            JSONObject jsonObject = JSONUtil.parseObj(options);
            question1.setOptions(jsonObject.toString());
            adds.add(question1);
        }
        return ResponseData.success(randQuestion);
    }

    @Override
    public ResponseData saveErrorQuestion(Question question) {
        UserVo currentUser = OauthUtils.getCurrentUser();

        question.setContext(currentUser.getUser().getId()+"");

        Integer record = baseMapper.getRecord(question);

        record red = new record();
        red.setUserId(Math.toIntExact(currentUser.getUser().getId()));
        red.setSituation("1");
        red.setQuestionId(question.getId());
        red.setDeleted("0");
        DateTime date = DateUtil.date();
        LocalDateTime localDateTime = date.toLocalDateTime();
        red.setCreateTime(localDateTime);
        //没有该错题记录,则新增
        if (record == 0 ){
            recordServer.save(red);
        }
        return ResponseData.success();
    }

    @Override
    public ResponseData getRecordErrorQuestion(page pages) {

        //获取当前用户
        UserVo currentUser = OauthUtils.getCurrentUser();

        BaseMapper<record> mapper = recordServer.getBaseMapper();

        //使用用户ID查询用户的错题记录
        Page<record> page =new Page<>(pages.getCurrent(),pages.getSize());
        QueryWrapper<record> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(record::getUserId,currentUser.getUser().getId())
                .eq(record::getDeleted,0);



        Page<record> recordPage = mapper.selectPage(page, queryWrapper);

        List<record> records = recordPage.getRecords();

        List <Question> lq = new ArrayList<>();

        for (record record : records) {

            lq.add(baseMapper.selectById(record.getQuestionId()));
        }
        Page<Question> data = new Page<>();
        data.setRecords(lq);
        data.setCurrent(recordPage.getCurrent());
        data.setSize(recordPage.getSize());


        return ResponseData.success(data);
    }

    @Override
    public ResponseData deleteErrorQuestion(Question question) {
        UserVo currentUser = OauthUtils.getCurrentUser();

        BaseMapper<record> recordBaseMapper = recordServer.getBaseMapper();

        QueryWrapper<record> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(record::getUserId,currentUser.getUser().getId())
                .eq(record::getQuestionId,question.getId())
                .eq(record::getDeleted,"0");
        record record = recordBaseMapper.selectOne(queryWrapper);

        if(ObjectUtil.isNotEmpty(record)){
            record.setDeleted("1");
        }else {
            ResponseData.error(510,"删除失败");
        }

        recordBaseMapper.updateById(record);


        return ResponseData.success("删除成功");
    }

    @Override
    public ResponseData getQuestionById(Question question) {

        Question datas = null;

        try{
            datas =  baseMapper.selectById(question.getId());
            String options = datas.getOptions();
            JSONObject jsonObject = JSONUtil.parseObj(options);
            datas.setOptions(jsonObject.toString());
        }catch (Exception e){
            ResponseData.error(510,"无效ID");
        }

        return ResponseData.success(datas);
    }


}
