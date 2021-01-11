package io.thisdk.github.ordering.dao.impl

import io.thisdk.github.ordering.bean.WechatUser
import io.thisdk.github.ordering.dao.WechatUserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component


@Component
class WechatUserDaoImpl : WechatUserDao {

    @Autowired
    lateinit var mongo: MongoTemplate

    override fun insert(wechatUser: WechatUser): WechatUser? {
        return mongo.save(wechatUser, "wechat_user")
    }

    override fun query(openid: String): WechatUser? {
        val query = Query(Criteria.where("openid").`is`(openid))
        return mongo.findOne(query, WechatUser::class.java, "wechat_user")
    }

    override fun deleteAll() {
        val query = Query()
        mongo.remove(query, WechatUser::class.java, "wechat_user")
    }

}