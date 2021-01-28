package io.thisdk.github.ordering.controller.program

import io.thisdk.github.ordering.bean.*
import io.thisdk.github.ordering.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/program/order")
class OrderController {

    @Autowired
    lateinit var orderService: OrderService

    @RequestMapping("/query")
    fun query(@RequestBody req: RestRequest<OpenIdReq>): RestResponse<List<Order>> {
        return RestResponse(orderService.queryOrderList(req.param))
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

}