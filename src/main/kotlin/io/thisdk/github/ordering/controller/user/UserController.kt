package io.thisdk.github.ordering.controller.user

import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.bean.User
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import io.thisdk.github.ordering.exception.OrderingErrorInfoException
import io.thisdk.github.ordering.jwt.JwtUtils
import io.thisdk.github.ordering.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
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

    @Value("\${token.jwt.header}")
    lateinit var jwtHeader: String

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @RequestMapping("/query")
    fun login(@RequestBody req: RestRequest<Unit>): RestResponse<User> {
        val servletRequestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val request = servletRequestAttributes.request
        val token = request.getHeader(jwtHeader) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.ERROR)
        val username = jwtUtils.getUserNameFromJwtToken(token)
        return RestResponse(userService.queryByUserName(username))
    }

    @RequestMapping("/update")
    fun update(@RequestBody req: RestRequest<User>): RestResponse<User> {
        return RestResponse(userService.insertUser(req.param))
    }

}