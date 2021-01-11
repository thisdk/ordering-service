package io.thisdk.github.ordering.controller.program

import io.thisdk.github.ordering.bean.OpenIdReq
import io.thisdk.github.ordering.bean.Order
import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController {

    @Autowired
    lateinit var orderService: OrderService

    @RequestMapping("/query")
    fun query(@RequestBody req: RestRequest<OpenIdReq>): RestResponse<List<Order>> {
        return RestResponse(data = orderService.getOrderList(req.param))
    }

}