package io.thisdk.github.ordering.service

import io.thisdk.github.ordering.bean.OpenIdReq
import io.thisdk.github.ordering.bean.WechatUser
import io.thisdk.github.ordering.dao.WechatUserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WechatUserService {

    @Autowired
    lateinit var wechatUserDao: WechatUserDao

    fun getUserInfo(openIdReq: OpenIdReq): WechatUser? {
        return wechatUserDao.query(openIdReq.openid)
    }

    fun insertUser(wechatUser: WechatUser): WechatUser? {
        return wechatUserDao.insert(wechatUser)
    }

    fun deleteAllUser(){
        wechatUserDao.deleteAll()
    }

}