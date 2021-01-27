package io.thisdk.github.ordering.security.filter;

import io.thisdk.github.ordering.bean.LoginUser;
import io.thisdk.github.ordering.service.TokenService;
import io.thisdk.github.ordering.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author jay
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{
    private final TokenService tokenService;

    @Autowired
    public JwtAuthenticationTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotEmpty(loginUser)){
            tokenService.verifyToken(loginUser);
        }
        chain.doFilter(request, response);
    }
}
