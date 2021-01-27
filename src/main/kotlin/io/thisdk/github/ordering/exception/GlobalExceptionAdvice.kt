package io.thisdk.github.ordering.exception

import io.thisdk.github.ordering.bean.RestResponse
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GlobalExceptionAdvice {

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleThrowable(request: HttpServletRequest, exception: Exception): RestResponse<Unit> {
        exception.printStackTrace()
        return RestResponse(OrderingErrorInfoEnum.ERROR)
    }

    @ExceptionHandler(BadCredentialsException::class)
    @ResponseBody
    fun handleThrowable(request: HttpServletRequest, exception: BadCredentialsException): RestResponse<Unit> {
        exception.printStackTrace()
        return RestResponse(OrderingErrorInfoEnum.LOGIN_ERROR)
    }

    @ExceptionHandler(InternalAuthenticationServiceException::class)
    @ResponseBody
    fun handleThrowable(
        request: HttpServletRequest,
        exception: InternalAuthenticationServiceException
    ): RestResponse<Unit> {
        exception.printStackTrace()
        return RestResponse(9999, exception.message ?: "InternalAuthenticationServiceException")
    }

    @ExceptionHandler(OrderingErrorInfoException::class)
    @ResponseBody
    fun handleThrowable(request: HttpServletRequest, exception: OrderingErrorInfoException): RestResponse<Unit> {
        exception.printStackTrace()
        return RestResponse(exception.code, exception.msg)
    }

}