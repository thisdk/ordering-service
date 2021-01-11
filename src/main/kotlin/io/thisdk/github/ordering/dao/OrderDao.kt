package io.thisdk.github.ordering.dao

import io.thisdk.github.ordering.bean.Order

interface OrderDao {

    fun query(openid: String): List<Order>?

}