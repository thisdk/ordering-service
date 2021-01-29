package io.thisdk.github.ordering.service

import io.thisdk.github.ordering.bean.*
import io.thisdk.github.ordering.dao.FoodDao
import io.thisdk.github.ordering.dao.OrderDao
import io.thisdk.github.ordering.dao.UserDao
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
    lateinit var userDao: UserDao

    @Autowired
    lateinit var foodDao: FoodDao

    fun queryOrderList(req: OpenIdReq): List<Order> {
        return orderDao.query(req.openid)
    }

    fun deleteOrder(orderId: String): Boolean {
        return orderDao.delete(orderId)
    }

    fun queryTodayOrder(): List<Order> {
        val calendar = Calendar.getInstance()
        calendar[calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH], 0, 0] = 0
        return orderDao.queryOrderByDate(calendar.time)
    }

    fun queryAllOrder(): List<Order> {
        return orderDao.query()
    }

    fun obtainFood(code: String): Order {
        val calendar = Calendar.getInstance()
        calendar[calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DATE], 0, 0] = 0
        calendar.add(Calendar.HOUR_OF_DAY, -8)
        val order = orderDao.queryOrderByDateAndCode(calendar.time, code)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.OBTAIN_NOT_ORDER)
        if (order.status != 1) throw OrderingErrorInfoException(OrderingErrorInfoEnum.OBTAIN_STATUS_ERROR)
        order.status = 2
        order.obtainTime = Date()
        return orderDao.update(order) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.OBTAIN_ERROR)
    }

    fun createOrder(cart: CartReq): Order {
        val user = userDao.query(cart.openid)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.USER_NOT_EXIST)
        val todayOrderList = queryTodayOrder()
        val code = 1111 + (Math.random() * 4).toInt() + (todayOrderList.size * 5)
        val order = Order(
            openid = cart.openid,
            nickName = user.nickname ?: "",
            createTime = Date(),
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
            phone = cart.phone,
            remark = cart.remark,
            refundPrice = 0,
            status = 1,
        )
        order.orderId =
            "3080${(Math.random() * 3080).toInt()}${System.currentTimeMillis()}${(Math.random() * 3080).toInt()}"
        return orderDao.insert(order) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.INSERT_ORDER_ERROR)
    }

}