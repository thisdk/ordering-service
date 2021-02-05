package io.thisdk.github.ordering.controller.auth

import io.thisdk.github.ordering.bean.LoginReq
import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.bean.User
import io.thisdk.github.ordering.jwt.JwtUtils
import io.thisdk.github.ordering.service.UserService
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

@RestController
@CrossOrigin
@RequestMapping("/auth")
class AuthController {

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
        return RestResponse(token)
    }

    @RequestMapping("/register")
    fun register(@RequestBody req: RestRequest<User>): RestResponse<Unit> {
        val user = req.param
        user.password = password.encode(user.password)
        userService.insertUser(user)
        return RestResponse()
    }

}