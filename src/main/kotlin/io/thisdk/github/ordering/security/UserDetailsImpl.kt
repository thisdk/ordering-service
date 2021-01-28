package io.thisdk.github.ordering.security

import com.fasterxml.jackson.annotation.JsonIgnore
import io.thisdk.github.ordering.bean.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class UserDetailsImpl(private val username: String, @field:JsonIgnore private val password: String) : UserDetails {

    private val authorities: Collection<GrantedAuthority> = ArrayList()

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        @JvmStatic
        fun build(user: User): UserDetailsImpl {
            return UserDetailsImpl(user.username, user.password)
        }
    }
}