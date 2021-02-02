package io.thisdk.github.ordering.dao

import io.thisdk.github.ordering.bean.Order

interface OrderDao {

    fun query(): List<Order>

    fun querySelfOrder(userId: String): List<Order>

    fun queryOrderByDate(time: Long): List<Order>

    fun insert(order: Order): Order?

    fun delete(orderId: String): Boolean

    fun update(order: Order): Order?

    fun queryOrderByDateAndCode(time: Long, code: String): Order?

}