package io.thisdk.github.ordering.bean

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "cms_user")
data class CmsUser(
    @Indexed(unique = true) val username: String,
    val password: String,
    val nickName: String,
) {
    @Id
    lateinit var id: String
}

