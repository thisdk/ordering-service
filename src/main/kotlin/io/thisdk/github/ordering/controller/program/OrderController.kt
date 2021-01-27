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
        return RestResponse(data = orderService.getOrderList(req.param))
    }

    @RequestMapping("/insert")
    fun insert(@RequestBody req: RestRequest<CartReq>): RestResponse<Order> {
        return RestResponse(data = orderService.inertOrder(req.param))
    }

    @RequestMapping("/delete")
    fun delete(@RequestBody req: RestRequest<OrderIdReq>): RestResponse<Unit> {
        return RestResponse(data = orderService.deleteOrder(req.param.orderId))
    }

    @RequestMapping("/takeMeal")
    fun takeMeal(@RequestBody req: RestRequest<CodeReq>): RestResponse<Order> {
        return RestResponse(data = orderService.takeMeal(req.param.code))
    }

}