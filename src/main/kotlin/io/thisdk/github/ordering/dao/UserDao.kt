package io.thisdk.github.ordering.dao

import io.thisdk.github.ordering.bean.User

interface UserDao {

    fun insert(user: User): User?

    fun query(username: String): User?

    fun delete(username: String): Boolean

    fun queryByMiniProgram(): List<User>

}