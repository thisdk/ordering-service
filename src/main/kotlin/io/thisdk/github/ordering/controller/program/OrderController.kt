package io.thisdk.github.ordering.controller.program

import io.thisdk.github.ordering.bean.*
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import io.thisdk.github.ordering.exception.OrderingErrorInfoException
import io.thisdk.github.ordering.jwt.JwtUtils
import io.thisdk.github.ordering.redis.RedisConfig
import io.thisdk.github.ordering.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@CrossOrigin
@RequestMapping("/program/order")
class OrderController {

    @Value("\${token.jwt.header}")
    lateinit var jwtHeader: String

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @Autowired
    lateinit var orderService: OrderService

    @RequestMapping("/querySelfOrder")
    fun querySelfOrder(@RequestBody req: RestRequest<Unit>, request: HttpServletRequest): RestResponse<List<Order>> {
        val token = request.getHeader(jwtHeader) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.ERROR)
        val username = jwtUtils.getUserNameFromJwtToken(token)
        return RestResponse(orderService.querySelfOrder(username))
    }

    @RequestMapping("/create")
    fun create(@RequestBody req: RestRequest<CartReq>): RestResponse<Order> {
        return RestResponse(orderService.createOrder(req.param))
    }

    @RequestMapping("/delete")
    fun delete(@RequestBody req: RestRequest<OrderIdReq>): RestResponse<Boolean> {
        return RestResponse(orderService.deleteOrder(req.param.orderId))
    }

    @RequestMapping("/obtainFood")
    fun obtainFood(@RequestBody req: RestRequest<CodeReq>): RestResponse<Order> {
        return RestResponse(orderService.obtainFood(req.param.code))
    }

    @RequestMapping("/queryTodayOrder")
    fun queryTodayOrder(@RequestBody req: RestRequest<Unit>): RestResponse<List<Order>> {
        return RestResponse(orderService.queryTodayOrder())
    }

    @Cacheable(cacheNames = [RedisConfig.REDIS_KEY_DATABASE], key = "'order-controller-query'")
    @RequestMapping("/queryAllOrder")
    fun queryAllOrder(@RequestBody req: RestRequest<Unit>): RestResponse<List<Order>> {
        return RestResponse(orderService.queryAllOrder())
    }

}