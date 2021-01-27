package io.thisdk.github.ordering.dao.impl

import io.thisdk.github.ordering.bean.Order
import io.thisdk.github.ordering.dao.OrderDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component
import java.util.*


@Component
class OrderDaoImpl : OrderDao {

    @Autowired
    lateinit var mongo: MongoTemplate

    override fun query(openid: String): List<Order> {
        val query = Query(Criteria.where("openid").`is`(openid))
        return mongo.find(query, Order::class.java, "wechat_order")
    }

    override fun queryOrderByDate(date: Date): List<Order> {
        val query = Query(Criteria.where("createTime").gte(date))
        return mongo.find(query, Order::class.java, "wechat_order")
    }

    override fun insert(order: Order): Order? {
        return mongo.save(order, "wechat_order")
    }

    override fun delete(orderId: String) {
        val query = Query(Criteria.where("orderId").`is`(orderId))
        mongo.remove(query, Order::class.java, "wechat_order")
    }

    override fun update(order: Order): Order? {
        return mongo.save(order, "wechat_order")
    }

    override fun queryOrderByDateAndCode(date: Date, code: String): Order? {
        val query = Query()
        query.addCriteria(Criteria.where("createTime").gte(date))
        query.addCriteria(Criteria.where("code").`is`(code))
        return mongo.findOne(query, Order::class.java, "wechat_order")
    }

}