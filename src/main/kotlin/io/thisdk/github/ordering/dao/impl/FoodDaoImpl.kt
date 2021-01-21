package io.thisdk.github.ordering.dao.impl

import io.thisdk.github.ordering.bean.Food
import io.thisdk.github.ordering.dao.FoodDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component


@Component
class FoodDaoImpl : FoodDao {

    @Autowired
    lateinit var mongo: MongoTemplate

    override fun insert(food: Food): Food? {
        return mongo.save(food, "wechat_food")
    }

    override fun delete(id: String) {
        val query = Query(Criteria.where("id").`is`(id))
        mongo.remove(query, Food::class.java, "wechat_food")
    }

    override fun query(): List<Food>? {
        val query = Query()
        return mongo.find(query, Food::class.java, "wechat_food")
    }

    override fun query(id: String): Food? {
        val query = Query(Criteria.where("id").`is`(id))
        return mongo.findOne(query, Food::class.java, "wechat_food")
    }


}