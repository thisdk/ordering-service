package io.thisdk.github.ordering.dao

import io.thisdk.github.ordering.bean.WechatUser

interface WechatUserDao {

    fun insert(wechatUser: WechatUser): WechatUser?

    fun query(openid: String): WechatUser?

    fun deleteAll()

}