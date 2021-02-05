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

    fun querySelfOrder(username: String): List<Order> {
        val user = userDao.queryByUsername(username)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.USER_NOT_EXIST)
        return orderDao.querySelfOrder(user.id)
    }

    fun deleteOrder(orderId: String): Boolean {
        return orderDao.delete(orderId)
    }

    fun queryTodayOrder(): List<Order> {
        return orderDao.queryOrderByDate(getToday().time)
    }

    fun queryAllOrder(): List<Order> {
        return orderDao.query()
    }

    fun obtainFood(code: String): Order {
        val order = orderDao.queryOrderByDateAndCode(getToday().time, code)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.OBTAIN_NOT_ORDER)
        if (order.status != 1) throw OrderingErrorInfoException(OrderingErrorInfoEnum.OBTAIN_STATUS_ERROR)
        order.status = 2
        order.obtainTime = Date().time
        order.updateTime = Date().time
        return orderDao.update(order) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.OBTAIN_ERROR)
    }

    fun createOrder(cart: CartReq): Order {
        val user = userDao.queryByUsername(cart.openid)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.USER_NOT_EXIST)
        val todayOrderList = queryTodayOrder()
        val code = 1111 + (Math.random() * 4).toInt() + (todayOrderList.size * 5)
        val now = Date().time
        val order = Order(
            userId = user.id,
            code = String.format("%04d", code),
            status = 1,
            createTime = now,
            updateTime = now,
            orderPrice = cart.total,
            amountPrice = cart.total,
            quantity = cart.quantity,
            list = cart.list.map {
                val food = foodDao.query(it.foodId)
                    ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.PARAM_ERROR)
                return@map OrderFood(
                    foodId = food.id,
                    foodName = food.title,
                    thumb = food.thumb ?: "",
                    price = food.price,
                    quantity = it.quantity
                )
            },
            refundPrice = 0
        )
        order.orderId = "3080${(Math.random() * 3080).toInt()}$now${(Math.random() * 3080).toInt()}"
        order.phone = cart.phone
        order.remark = cart.remark
        return orderDao.insert(order) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.INSERT_ORDER_ERROR)
    }

    private fun getToday(): Date {
        val calendar = Calendar.getInstance()
        calendar[calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH], 0, 0] = 0
        calendar[Calendar.MILLISECOND] = 0
        return calendar.time
    }

}