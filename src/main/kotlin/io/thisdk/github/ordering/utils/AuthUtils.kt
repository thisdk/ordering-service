package io.thisdk.github.ordering.utils

import org.springframework.util.StringUtils
import javax.servlet.http.HttpServletRequest

object AuthUtils {

    fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader("Authorization")
        return if (StringUtils.hasText(headerAuth)) {
            headerAuth
        } else null
    }

}