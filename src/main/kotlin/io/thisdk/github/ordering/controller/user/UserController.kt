package io.thisdk.github.ordering.controller.user

import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.bean.User
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import io.thisdk.github.ordering.exception.OrderingErrorInfoException
import io.thisdk.github.ordering.security.jwt.JwtUtils
import io.thisdk.github.ordering.service.UserService
import io.thisdk.github.ordering.utils.AuthUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @RequestMapping("/query")
    fun login(@RequestBody req: RestRequest<Unit>): RestResponse<User> {
        val servletRequestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?
        val request = servletRequestAttributes!!.request
        val token = AuthUtils.parseJwt(request) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.ERROR)
        val username = jwtUtils.getUserNameFromJwtToken(token)
        return RestResponse(userService.queryByUserName(username))
    }

}