package io.thisdk.github.ordering.controller.program

import io.thisdk.github.ordering.bean.*
import io.thisdk.github.ordering.dto.RestRequest
import io.thisdk.github.ordering.dto.RestResponse
import io.thisdk.github.ordering.dto.req.CartReq
import io.thisdk.github.ordering.dto.req.CodeReq
import io.thisdk.github.ordering.dto.req.OrderIdReq
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import io.thisdk.github.ordering.exception.OrderingErrorInfoException
import io.thisdk.github.ordering.jwt.JwtUtils
import io.thisdk.github.ordering.redis.RedisConfig
import io.thisdk.github.ordering.service.OrderService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@CrossOrigin
@RequestMapping("/program/order")
class OrderController {

    companion object {
        private val logger = LoggerFactory.getLogger(OrderController::class.java)
    }

    @Value("\${token.jwt.header}")
    lateinit var jwtHeader: String

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @Autowired
    lateinit var orderService: OrderService

    @Cacheable(cacheNames = [RedisConfig.REDIS_KEY_DATABASE], key = "'order-controller-query-today'")
    @RequestMapping("/queryTodayOrder")
    @PreAuthorize("hasRole('ADMIN')")
    fun queryTodayOrder(@RequestBody req: RestRequest<Unit>): RestResponse<List<Order>> {
        return RestResponse(orderService.queryTodayOrder())
    }

    @Cacheable(cacheNames = [RedisConfig.REDIS_KEY_DATABASE], key = "'order-controller-query-all'")
    @RequestMapping("/queryAllOrder")
    @PreAuthorize("hasRole('ADMIN')")
    fun queryAllOrder(@RequestBody req: RestRequest<Unit>): RestResponse<List<Order>> {
        return RestResponse(orderService.queryAllOrder())
    }

    @RequestMapping("/obtainFood")
    @PreAuthorize("hasRole('ADMIN')")
    fun obtainFood(@RequestBody req: RestRequest<CodeReq>): RestResponse<Order> {
        logger.info("OrderController obtainFood code : {}", req.param.code)
        return RestResponse(orderService.obtainFood(req.param.code))
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    fun delete(@RequestBody req: RestRequest<OrderIdReq>): RestResponse<Boolean> {
        return RestResponse(orderService.deleteOrder(req.param.orderId))
    }

    @RequestMapping("/create")
    @PreAuthorize("hasRole('USER')")
    fun create(@RequestBody req: RestRequest<CartReq>): RestResponse<Order> {
        return RestResponse(orderService.createOrder(req.param))
    }

    @RequestMapping("/querySelfOrder")
    @PreAuthorize("hasRole('USER')")
    fun querySelfOrder(@RequestBody req: RestRequest<Unit>, request: HttpServletRequest): RestResponse<List<Order>> {
        val token = request.getHeader(jwtHeader) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.ERROR)
        val username = jwtUtils.getUserNameFromJwtToken(token)
        val orders = orderService.querySelfOrder(username)
        logger.info("OrderController querySelfOrder user {} order : {}", username, orders)
        return RestResponse(orders)
    }

}