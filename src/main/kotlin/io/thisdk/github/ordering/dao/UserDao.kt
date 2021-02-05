package io.thisdk.github.ordering.dao

import io.thisdk.github.ordering.bean.User

interface UserDao {

    fun insert(user: User): User?

    fun queryByUsername(username: String): User?

    fun queryByUserId(id: String): User?

    fun deleteByUsername(username: String): Boolean

    fun deleteByUserId(id: String): Boolean

    fun queryAllMiniProgramUser(): List<User>

}