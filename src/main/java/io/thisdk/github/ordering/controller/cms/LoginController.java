package io.thisdk.github.ordering.controller.cms;

import io.thisdk.github.ordering.bean.CmsUser;
import io.thisdk.github.ordering.bean.LoginReq;
import io.thisdk.github.ordering.bean.RestRequest;
import io.thisdk.github.ordering.bean.RestResponse;
import io.thisdk.github.ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cms/user")
public class LoginController {

    private final UserService service;

    @Autowired
    public LoginController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/login")
    public RestResponse<String> login(@RequestBody RestRequest<LoginReq> req) {
        return new RestResponse<>(service.login(req.getParam().getUsername(), req.getParam().getPassword()));
    }

    @RequestMapping("/getCurrentUser")
    public RestResponse<CmsUser> getCurrentUser(@RequestBody RestRequest<Void> req) {
        return new RestResponse<>(service.getCurrentUserInfo());
    }

}
