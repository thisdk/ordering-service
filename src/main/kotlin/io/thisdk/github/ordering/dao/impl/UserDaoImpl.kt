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
        return mongo.save(user, "wechat_user")
    }

    override fun query(openid: String): User? {
        val query = Query(Criteria.where("openid").`is`(openid))
        return mongo.findOne(query, User::class.java, "wechat_user")
    }

    override fun deleteAll() {
        val query = Query()
        mongo.remove(query, User::class.java, "wechat_user")
    }

}