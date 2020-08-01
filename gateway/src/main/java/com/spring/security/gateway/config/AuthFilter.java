package com.spring.security.gateway.config;

import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.net.URLCodec;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.*;

/**
 * @author luyanan
 * @since 2020/8/1
 * <p>解析token,转发明文token 至内部服务</p>
 **/
public class AuthFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        //1. 获取令牌内容
        RequestContext ctx = RequestContext.getCurrentContext();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2Authentication)) {

            // 无token 访问资源的情况,目前只有`uaa` 服务直接暴露
            return null;
        }

        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;

        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        Object principal = userAuthentication.getPrincipal();
        // 2. 组装明文token,转发给内部服务,放入header中,名称为 json-token
        List<String> authorities = new ArrayList<>();
        userAuthentication.getAuthorities().stream().forEach(a -> {
            authorities.add(((GrantedAuthority) a).getAuthority());
        });

        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Map<String, String> requestParameters =
                oAuth2Request.getRequestParameters();

        Map<String, Object> jsonToken = new HashMap<>(requestParameters);
        if (null != userAuthentication) {
            jsonToken.put("principal", userAuthentication.getName());
            jsonToken.put("authorities", authorities);
        }

        ctx.addZuulRequestHeader("json-token", URLUtil.encode(JSON.toJSONString(jsonToken), "utf-8"));
        return null;
    }


}
