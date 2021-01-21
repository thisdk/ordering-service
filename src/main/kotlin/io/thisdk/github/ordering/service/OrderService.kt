package io.thisdk.github.ordering.service

import io.thisdk.github.ordering.bean.*
import io.thisdk.github.ordering.dao.FoodDao
import io.thisdk.github.ordering.dao.OrderDao
import io.thisdk.github.ordering.dao.WechatUserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService {

    @Autowired
    lateinit var orderDao: OrderDao

    @Autowired
    lateinit var userDao: WechatUserDao

    @Autowired
    lateinit var foodDao: FoodDao

    fun getOrderList(req: OpenIdReq): List<Order>? {
        return orderDao.query(req.openid)
    }

    fun inertOrder(cart: CartReq): Order? {
        val user = userDao.query(cart.openid)!!
        val order = Order(
            openid = cart.openid,
            nickName = user.nickName,
            createTime = Date(),
            status = 1,
            orderPrice = cart.total,
            amountPrice = cart.total,
            code = "51080",
            list = cart.list.map {
                val food = foodDao.query(it.foodId)!!
                return@map OrderFood(
                    it.foodId,
                    food.title,
                    food.thumb ?: "",
                    food.price,
                    it.quantity
                )
            },
            payTime = Date(),
            payType = 0,
            phone = "",
            remark = "",
            refundPrice = 0
        )
        return orderDao.insert(order)
    }

}