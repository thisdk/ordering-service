package io.thisdk.github.ordering.controller.auth

import io.thisdk.github.ordering.bean.LoginReq
import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.bean.User
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import io.thisdk.github.ordering.exception.OrderingErrorInfoException
import io.thisdk.github.ordering.jwt.JwtUtils
import io.thisdk.github.ordering.role.Role
import io.thisdk.github.ordering.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.HashSet

@RestController
@CrossOrigin
@RequestMapping("/auth")
class AuthController {

    companion object {
        private val logger = LoggerFactory.getLogger(AuthController::class.java)
    }

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var password: PasswordEncoder

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @RequestMapping("/login")
    fun login(@RequestBody req: RestRequest<LoginReq>, request: HttpServletRequest): RestResponse<String> {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(req.param.username, req.param.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val token = jwtUtils.generateJwtToken(authentication)
        val username = jwtUtils.getUserNameFromJwtToken(token)
        userService.queryByUserName(username).apply {
            lastLoginTime = Date().time
            userService.insertUser(this)
        }
        logger.info("user {} login success !", username)
        return RestResponse(token)
    }

    @RequestMapping("/register")
    fun register(@RequestBody req: RestRequest<User>): RestResponse<Unit> {
        val user = req.param
        if (userService.hasUserByUserName(user.username)) {
            throw OrderingErrorInfoException(OrderingErrorInfoEnum.USER_EXIST)
        }
        user.password = password.encode(user.password)
        user.roles = HashSet(listOf(Role.ROLE_USER.name))
        userService.insertUser(user)
        logger.info("user {} register success !", user.username)
        return RestResponse()
    }

}