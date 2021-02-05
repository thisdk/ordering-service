package io.thisdk.github.ordering.controller.user

import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.bean.User
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import io.thisdk.github.ordering.exception.OrderingErrorInfoException
import io.thisdk.github.ordering.jwt.JwtUtils
import io.thisdk.github.ordering.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


@RestController
@CrossOrigin
@RequestMapping("/user")
class UserController {

    companion object {
        private val logger = LoggerFactory.getLogger(UserController::class.java)
    }

    @Value("\${token.jwt.header}")
    lateinit var jwtHeader: String

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @Autowired
    lateinit var userService: UserService

    @RequestMapping("/query")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    fun query(@RequestBody req: RestRequest<Unit>, request: HttpServletRequest): RestResponse<User> {
        val token = request.getHeader(jwtHeader) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.ERROR)
        val username = jwtUtils.getUserNameFromJwtToken(token)
        val user = userService.queryByUserName(username)
        logger.info("UserController query : {}", user)
        return RestResponse(user)
    }

    @RequestMapping("/update")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    fun update(@RequestBody req: RestRequest<User>): RestResponse<User> {
        logger.info("UserController update : {}", req.param)
        val user = userService.queryByUserId(req.param.id)
        user.openid = req.param.openid
        user.nickname = req.param.nickname
        user.avatar = req.param.avatar
        user.city = req.param.city
        return RestResponse(userService.insertUser(user))
    }

    @RequestMapping("/queryMiniProgramUser")
    @PreAuthorize("hasRole('ADMIN')")
    fun queryMiniProgramUser(@RequestBody req: RestRequest<Unit>): RestResponse<List<User>> {
        return RestResponse(userService.queryAllMiniProgramUser())
    }

}