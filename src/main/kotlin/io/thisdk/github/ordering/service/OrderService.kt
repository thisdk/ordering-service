package io.thisdk.github.ordering.service

import io.thisdk.github.ordering.bean.*
import io.thisdk.github.ordering.dao.FoodDao
import io.thisdk.github.ordering.dao.OrderDao
import io.thisdk.github.ordering.dao.WechatUserDao
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import io.thisdk.github.ordering.exception.OrderingErrorInfoException
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

    fun getOrderList(req: OpenIdReq): List<Order> {
        return orderDao.query(req.openid)
    }

    fun deleteOrder(orderId: String) {
        orderDao.delete(orderId)
    }

    fun takeMeal(code: String): Order {
        val calendar = Calendar.getInstance()
        calendar[calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH], 0, 0] = 0
        val order = orderDao.queryOrderByDateAndCode(calendar.time, code)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.TAKE_MEAL_NOT_ORDER)
        if (order.status != 1) throw OrderingErrorInfoException(OrderingErrorInfoEnum.TAKE_MEAL_STATUS_ERROR)
        order.status = 2
        order.takeMealTime = Date()
        return orderDao.update(order) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.TAKE_MEAL_ERROR)
    }

    fun inertOrder(cart: CartReq): Order {
        val user = userDao.query(cart.openid)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.USER_NOT_EXIST)
        val calendar = Calendar.getInstance()
        calendar[calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH], 0, 0] = 0
        val todayOrderList = orderDao.queryOrderByDate(calendar.time)
        val code = 1111 + ((Math.random() * 4).toInt() + 1) + (todayOrderList.size * 5)
        val order = Order(
            openid = cart.openid,
            nickName = user.nickName,
            createTime = Date(),
            status = 1,
            orderPrice = cart.total,
            amountPrice = cart.total,
            quantity = cart.quantity,
            code = String.format("%04d", code),
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
            phone = cart.phone,
            remark = cart.remark,
            refundPrice = 0
        )
        order.orderId =
            "10086${(Math.random() * 10086).toInt()}${System.currentTimeMillis()}${(Math.random() * 1008600).toInt()}"
        order.payId =
            "53866${(Math.random() * 53866).toInt()}${System.currentTimeMillis()}${(Math.random() * 5386600).toInt()}"
        return orderDao.insert(order)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.INSERT_ORDER_ERROR)
    }

}