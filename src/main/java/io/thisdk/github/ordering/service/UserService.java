package io.thisdk.github.ordering.service;

import io.thisdk.github.ordering.bean.CmsUser;
import io.thisdk.github.ordering.bean.LoginUser;
import io.thisdk.github.ordering.dao.impl.UserAccountDao;
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum;
import io.thisdk.github.ordering.exception.OrderingErrorInfoException;
import io.thisdk.github.ordering.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final
    TokenService tokenService;

    final
    UserAccountDao userAccountDao;

    @Autowired
    public UserService(TokenService tokenService, UserAccountDao userAccountDao) {
        this.tokenService = tokenService;
        this.userAccountDao = userAccountDao;
    }

    public String login(String username, String password) {
        CmsUser cmsUser = userAccountDao.getUserInfo(username, password);
        if (cmsUser != null) {
            LoginUser loginUser = new LoginUser(cmsUser);
            return tokenService.createToken(loginUser);
        } else throw new OrderingErrorInfoException(OrderingErrorInfoEnum.LOGIN_ERROR);

    }

    public CmsUser getCurrentUserInfo() {
        LoginUser loginUser = tokenService.getLoginUser(StringUtils.getRequest());
        return loginUser.getUser();
    }
}
