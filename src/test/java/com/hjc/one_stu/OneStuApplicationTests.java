package com.hjc.one_stu;


import com.alibaba.fastjson.JSONObject;
import com.hjc.one_stu.Dao.User;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class OneStuApplicationTests {

    static int ids = 1;

   public static void contextLoads() {
        String USER = "xiaoq";
        String PASS = "xiaoq";
        String DB_URL="jdbc:mysql://49.232.14.18:3306/21db_test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true";
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        Connection conn = null;
        Statement stmt = null;
       List<HashMap> list = new ArrayList(1000);
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "select question,optionA,optionB,optionC,optionD,answer,resolving,subjectid,source from common_course_choice_copy where subjectId = "+ids+" limit 1000;";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("subjectId");
                String question = rs.getString("question");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                String resolving = rs.getString("resolving");

                options build = options.builder().optionA(optionA).optionB(optionB).optionC(optionC).optionD(optionD).build();

                String s = JSONObject.toJSON(build).toString();
                HashMap<String, String> mps = new HashMap<>();
                mps.put("question",question);
                mps.put("option",s);
                mps.put("answer",answer);
                mps.put("resolving",resolving);
                list.add(mps);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        insert(list);
    }

   public static void insert(List<HashMap> list) {
       String USER = "xiaoq";
       String PASS = "xiaoq";
       String DB_URL="jdbc:mysql://49.232.14.18:3306/onedu?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true";
       String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
       Connection conn = null;
       Statement stmt = null;

       try{
           // 注册 JDBC 驱动
           Class.forName(JDBC_DRIVER);

           // 打开链接
           System.out.println("连接数据库...");
           conn = DriverManager.getConnection(DB_URL,USER,PASS);

           // 执行查询
           System.out.println(" 实例化Statement对象...");
           stmt = conn.createStatement();
           String sql;

           for (HashMap map : list) {
               try{
                   sql = "INSERT INTO `onedu`.`question`(`context`, `parentId`, `options`, `answer`, `resolving`) VALUES ('"+map.get("question")+"', '"+ids+"', '"+map.get("option")+"','"+map.get("answer")+"', '"+map.get("resolving")+"');";
                   boolean rs = stmt.execute(sql);
                   log.info("插入成功");
               }catch (Exception e){
                   continue;
               }
           }

           // 完成后关闭
           stmt.close();
           conn.close();
       }catch(SQLException se){
           // 处理 JDBC 错误
           se.printStackTrace();
       }catch(Exception e){
           // 处理 Class.forName 错误
           e.printStackTrace();
       }finally{
           // 关闭资源
           try{
               if(stmt!=null) stmt.close();
           }catch(SQLException se2){
           }// 什么都不做
           try{
               if(conn!=null) conn.close();
           }catch(SQLException se){
               se.printStackTrace();
           }
       }
       System.out.println("Goodbye!");

   }

    public static void main(String[] args) {
        for (;  ids<=9 ; ids++) {
            contextLoads();
        }
    }

}

@Data
@Builder
class options{
    String optionA;
    String optionB;
    String optionC;
    String optionD;

}
