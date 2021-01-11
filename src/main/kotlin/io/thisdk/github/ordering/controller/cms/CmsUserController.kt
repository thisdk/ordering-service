package io.thisdk.github.ordering.controller.cms

import io.thisdk.github.ordering.bean.CmsUser
import io.thisdk.github.ordering.bean.LoginReq
import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.service.CmsUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cms/user")
class CmsUserController {

    @Autowired
    lateinit var cmsUserService: CmsUserService

    @PostMapping("/login")
    fun login(@RequestBody req: RestRequest<LoginReq>): RestResponse<CmsUser> {
        return RestResponse(data = cmsUserService.login(req.param))
    }

    @PostMapping("/register")
    fun register(@RequestBody req: RestRequest<CmsUser>): RestResponse<CmsUser> {
        return RestResponse(data = cmsUserService.register(req.param))
    }

}