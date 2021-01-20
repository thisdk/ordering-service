package io.thisdk.github.ordering.controller.cms;

import io.thisdk.github.ordering.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * </p>
*
* @author jay
* @date 2021-01-07 16:04:58
* @version
*/
@RestController
@RequestMapping("/cms/user")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService service;

    /**
     * 获取所有等待叫号列表
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String loginName, String password){
        return service.login(loginName,password);
    }

}
