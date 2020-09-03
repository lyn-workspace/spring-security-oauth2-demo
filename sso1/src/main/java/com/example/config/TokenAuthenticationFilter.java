package com.example.config;

import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.UserEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luyanan
 * @since 2020/8/1
 * <p>定义filter拦截token，并形成Spring Security的Authentication对象</p>
 **/
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("json-token");

        if (null != token) {
            // 1. 解析token
            String decode = URLUtil.decode(token, "utf-8");
            JSONObject userJson = JSON.parseObject(decode);
            String principal = userJson.getString("principal");

            UserEntity userEntity = JSON.parseObject(principal, UserEntity.class);

            JSONArray authoritiesArray = userJson.getJSONArray("authorities");

            String[] authorities = authoritiesArray.toArray(new
                    String[authoritiesArray.size()]);

            // 2. 新建并填充 authentication
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEntity, null, AuthorityUtils.createAuthorityList(authorities));
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 3.将authentication 保存到上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(request, response);
    }
}
