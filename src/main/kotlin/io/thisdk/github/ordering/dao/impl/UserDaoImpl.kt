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

    override fun queryByUsername(username: String): User? {
        val query = Query(Criteria.where("username").`is`(username))
        return mongo.findOne(query, User::class.java, "user")
    }

    override fun queryByUserId(id: String): User? {
        val query = Query(Criteria.where("id").`is`(id))
        return mongo.findOne(query, User::class.java, "user")
    }

    override fun deleteByUsername(username: String): Boolean {
        val query = Query(Criteria.where("username").`is`(username))
        val result = mongo.remove(query, User::class.java, "user")
        return result.deletedCount > 0
    }

    override fun deleteByUserId(id: String): Boolean {
        val query = Query(Criteria.where("id").`is`(id))
        val result = mongo.remove(query, User::class.java, "user")
        return result.deletedCount > 0
    }

    override fun queryAllMiniProgramUser(): List<User> {
        val query = Query(Criteria.where("openid").ne(null))
        return mongo.find(query, User::class.java, "user")
    }

}