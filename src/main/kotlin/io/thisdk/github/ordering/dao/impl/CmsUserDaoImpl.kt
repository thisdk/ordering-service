package io.thisdk.github.ordering.dao.impl

import io.thisdk.github.ordering.bean.CmsUser
import io.thisdk.github.ordering.dao.CmsUserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component


@Component
class CmsUserDaoImpl : CmsUserDao {

    @Autowired
    lateinit var mongo: MongoTemplate

    override fun login(username: String, password: String): CmsUser? {
        val query = Query()
        query.addCriteria(Criteria.where("username").`is`(username))
        query.addCriteria(Criteria.where("password").`is`(password))
        return mongo.findOne(query, CmsUser::class.java, "cms_user")
    }

    override fun register(cmsUser: CmsUser): CmsUser? {
        return mongo.save(cmsUser, "cms_user")
    }


}