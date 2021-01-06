package io.thisdk.github.ordering.controller.program

import io.thisdk.github.ordering.bean.QueryUserReq
import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.bean.User
import io.thisdk.github.ordering.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/insert")
    fun insert(@RequestBody req: RestRequest<User>): RestResponse<User> {
        return RestResponse(data = userService.insertUser(req.param))
    }

    @RequestMapping("/query")
    fun query(@RequestBody req: RestRequest<QueryUserReq>): RestResponse<User> {
        return RestResponse(data = userService.getUserInfo(req.param))
    }

    @RequestMapping("/delete")
    fun delete(@RequestBody req: RestRequest<Unit>): RestResponse<Unit> {
        return RestResponse(data = userService.deleteAllUser())
    }

}