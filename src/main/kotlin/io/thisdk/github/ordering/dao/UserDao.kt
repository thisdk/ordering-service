package io.thisdk.github.ordering.dao

import io.thisdk.github.ordering.bean.User

interface UserDao {

    fun insert(user: User): User?

    fun query(openid: String): User?

    fun deleteAll()

}