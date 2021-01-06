package io.thisdk.github.ordering.bean

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "wechat_user")
data class User(
    @Indexed val openid: String,
    val nickName: String,
    val avatarUrl: String,
    val city: String
) {
    @Id
    lateinit var id: String
}
