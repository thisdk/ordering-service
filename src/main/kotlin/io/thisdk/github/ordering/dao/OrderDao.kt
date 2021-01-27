package io.thisdk.github.ordering.dao

import io.thisdk.github.ordering.bean.Order
import java.util.*

interface OrderDao {

    fun query(openid: String): List<Order>

    fun queryOrderByDate(date: Date): List<Order>

    fun insert(order: Order): Order?

    fun delete(orderId: String)

    fun update(order: Order): Order?

    fun queryOrderByDateAndCode(date: Date, code: String): Order?

}