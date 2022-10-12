package com.hjc.one_stu.Utils.jwt;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author csz
 * @Date 2022/10/7 01:58
 */
public class URI {
    private static List<String> fxUrI = new ArrayList<>();

    private static void add(){

        fxUrI.add("/user/login");
        fxUrI.add("/user/register");
    }

    public static List getFxUrI() {
        if (fxUrI.size()==0){
            add();
        }
        return fxUrI;
    }
}
