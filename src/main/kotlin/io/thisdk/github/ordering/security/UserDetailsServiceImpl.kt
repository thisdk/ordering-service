package io.thisdk.github.ordering.security

import io.thisdk.github.ordering.security.UserDetailsImpl.Companion.build
import io.thisdk.github.ordering.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    lateinit var userService: UserService

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userService.queryByUserName(username)
        return build(user)
    }

}