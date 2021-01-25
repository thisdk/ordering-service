package io.thisdk.github.ordering.service

import io.thisdk.github.ordering.bean.OpenIdReq
import io.thisdk.github.ordering.bean.WechatUser
import io.thisdk.github.ordering.dao.WechatUserDao
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import io.thisdk.github.ordering.exception.OrderingErrorInfoException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WechatUserService {

    @Autowired
    lateinit var wechatUserDao: WechatUserDao

    fun getUserInfo(openIdReq: OpenIdReq): WechatUser {
        return wechatUserDao.query(openIdReq.openid)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.USER_NOT_EXIST)
    }

    fun insertUser(wechatUser: WechatUser): WechatUser {
        return wechatUserDao.insert(wechatUser)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.USER_NOT_EXIST)
    }

    fun deleteAllUser() {
        wechatUserDao.deleteAll()
    }

}