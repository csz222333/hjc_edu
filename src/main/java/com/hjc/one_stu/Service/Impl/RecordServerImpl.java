package com.hjc.one_stu.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.one_stu.Dao.record;
import com.hjc.one_stu.Mapper.RecordMapper;
import com.hjc.one_stu.Service.Interfaces.RecordServer;
import org.springframework.stereotype.Service;

/**
 * @Author csz
 * @Date 2022/10/10 23:32
 */
@Service
public class RecordServerImpl extends ServiceImpl<RecordMapper, record> implements RecordServer {

}
