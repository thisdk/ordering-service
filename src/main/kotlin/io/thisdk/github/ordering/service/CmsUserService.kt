package io.thisdk.github.ordering.service

import io.thisdk.github.ordering.bean.CmsUser
import io.thisdk.github.ordering.bean.LoginReq
import io.thisdk.github.ordering.dao.CmsUserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CmsUserService {

    @Autowired
    lateinit var cmsUserDao: CmsUserDao

    fun login(req: LoginReq): CmsUser? {
        return cmsUserDao.login(req.username, req.username)
    }

    fun register(req: CmsUser): CmsUser? {
        return cmsUserDao.register(req)
    }

}