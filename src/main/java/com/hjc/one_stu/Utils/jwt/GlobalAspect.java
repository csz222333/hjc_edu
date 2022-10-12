package com.hjc.one_stu.Utils.jwt;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hjc.one_stu.Dao.UserVO.UserVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author csz
 * @Date 2022/9/22 16:38
 */
@Configuration
public class GlobalAspect extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("jwt-token");

        String requestURI = request.getRequestURI();

        List<String> fxUrI = URI.getFxUrI();

        for (String url : fxUrI) {
            if(url.equals(requestURI)){
                filterChain.doFilter(request,response);
                return;
            }
        }

        String s = PassWorldUtils.decrypContext(token);

        UserVo userVo = OauthUtils.tokenToUserVo(s);

        if(userVo.getExFlag()){
            response.setStatus(HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED);
            return;
        }
        filterChain.doFilter(request,response);

    }
}
