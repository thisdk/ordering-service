package io.thisdk.github.ordering.service

import io.thisdk.github.ordering.bean.OpenIdReq
import io.thisdk.github.ordering.bean.Order
import io.thisdk.github.ordering.dao.OrderDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService {

    @Autowired
    lateinit var orderDao: OrderDao

    fun getOrderList(req: OpenIdReq): List<Order>? {
        return orderDao.query(req.openid)
    }

}