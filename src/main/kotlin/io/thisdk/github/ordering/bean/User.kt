package io.thisdk.github.ordering.bean

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class User(
    @Indexed(unique = true) val username: String,
    var password: String,
) {
    @Id
    lateinit var id: String
    var openid: String? = null
    var nickname: String? = null
    var avatar: String? = null
    var city: String? = null
    var lastLoginTime: Long? = null
}
