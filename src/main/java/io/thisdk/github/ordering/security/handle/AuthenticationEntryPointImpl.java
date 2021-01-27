package io.thisdk.github.ordering.security.handle;

import com.alibaba.fastjson.JSON;
import io.thisdk.github.ordering.utils.AjaxResult;
import io.thisdk.github.ordering.utils.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        StringUtils.renderString(response, JSON.toJSONString(AjaxResult.error(-10086, "unAuthorization")));
    }

}
