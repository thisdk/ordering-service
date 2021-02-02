package io.thisdk.github.ordering.dao.impl

import io.thisdk.github.ordering.bean.Order
import io.thisdk.github.ordering.dao.OrderDao
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component


@Component
class OrderDaoImpl : OrderDao {

    companion object {
        private val loggerInstance = LoggerFactory.getLogger(OrderDaoImpl::class.java)
    }

    @Autowired
    lateinit var mongo: MongoTemplate

    override fun query(): List<Order> {
        return mongo.findAll(Order::class.java, "wechat_order")
    }

    override fun querySelfOrder(userId: String): List<Order> {
        val query = Query(Criteria.where("userId").`is`(userId))
        return mongo.find(query, Order::class.java, "wechat_order")
    }

    override fun queryOrderByDate(time: Long): List<Order> {
        loggerInstance.info("queryOrderByDate time : {}", time)
        val query = Query(Criteria.where("createTime").gt(time))
        return mongo.find(query, Order::class.java, "wechat_order")
    }

    override fun insert(order: Order): Order? {
        return mongo.save(order, "wechat_order")
    }

    override fun delete(orderId: String): Boolean {
        val query = Query(Criteria.where("orderId").`is`(orderId))
        val result = mongo.remove(query, Order::class.java, "wechat_order")
        return result.deletedCount > 0
    }

    override fun update(order: Order): Order? {
        return mongo.save(order, "wechat_order")
    }

    override fun queryOrderByDateAndCode(time: Long, code: String): Order? {
        val query = Query()
        query.addCriteria(Criteria.where("createTime").gt(time))
        query.addCriteria(Criteria.where("code").`is`(code))
        return mongo.findOne(query, Order::class.java, "wechat_order")
    }

}