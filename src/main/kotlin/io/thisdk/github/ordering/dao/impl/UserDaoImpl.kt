package io.thisdk.github.ordering.dao.impl

import io.thisdk.github.ordering.bean.User
import io.thisdk.github.ordering.dao.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class UserDaoImpl : UserDao {

    @Autowired
    lateinit var mongo: MongoTemplate

    override fun insert(user: User): User? {
        return mongo.save(user, "user")
    }

    override fun query(username: String): User? {
        val query = Query(Criteria.where("username").`is`(username))
        return mongo.findOne(query, User::class.java, "user")
    }

    override fun delete(username: String): Boolean {
        val query = Query(Criteria.where("username").`is`(username))
        val result = mongo.remove(query, User::class.java, "user")
        return result.deletedCount > 0
    }


}