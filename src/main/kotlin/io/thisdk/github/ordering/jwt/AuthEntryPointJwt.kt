package io.thisdk.github.ordering.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthEntryPointJwt : AuthenticationEntryPoint {

    companion object {
        private val logger = LoggerFactory.getLogger(AuthEntryPointJwt::class.java)
    }

    @Autowired
    lateinit var mapper: ObjectMapper

    @Throws(IOException::class)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        logger.error("Unauthorized error: {}", authException.message)
        response.writer.write(mapper.writeValueAsString(RestResponse<Unit>(OrderingErrorInfoEnum.AUTH_ERROR)))
    }

}