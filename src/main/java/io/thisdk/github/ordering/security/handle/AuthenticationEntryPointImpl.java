package io.thisdk.github.ordering.security.handle;

import com.alibaba.fastjson.JSONObject;
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum;
import io.thisdk.github.ordering.exception.OrderingErrorInfoException;
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
        StringUtils.renderString(response, JSONObject.toJSONString(new OrderingErrorInfoException(OrderingErrorInfoEnum.AUTH_ERROR)));
    }

}
