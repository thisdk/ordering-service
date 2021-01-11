package io.thisdk.github.ordering.controller.program

import io.thisdk.github.ordering.bean.OpenIdReq
import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.bean.WechatUser
import io.thisdk.github.ordering.service.WechatUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/program/user")
class WechatUserController {

    @Autowired
    lateinit var wechatUserService: WechatUserService

    @PostMapping("/insert")
    fun insert(@RequestBody req: RestRequest<WechatUser>): RestResponse<WechatUser> {
        return RestResponse(data = wechatUserService.insertUser(req.param))
    }

    @RequestMapping("/query")
    fun query(@RequestBody req: RestRequest<OpenIdReq>): RestResponse<WechatUser> {
        return RestResponse(data = wechatUserService.getUserInfo(req.param))
    }

    @RequestMapping("/delete")
    fun delete(@RequestBody req: RestRequest<Unit>): RestResponse<Unit> {
        return RestResponse(data = wechatUserService.deleteAllUser())
    }

}