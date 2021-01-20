package io.thisdk.github.ordering.util.filter;

import io.thisdk.github.ordering.util.StringUtils;
import io.thisdk.github.ordering.util.bean.LoginUser;
import io.thisdk.github.ordering.util.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
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
        if (StringUtils.isNotEmpty(loginUser))
        {
            tokenService.verifyToken(loginUser);
        }
        chain.doFilter(request, response);
    }
}
