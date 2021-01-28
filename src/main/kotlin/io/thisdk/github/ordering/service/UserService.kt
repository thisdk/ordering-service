package io.thisdk.github.ordering.service

import io.thisdk.github.ordering.bean.User
import io.thisdk.github.ordering.dao.UserDao
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import io.thisdk.github.ordering.exception.OrderingErrorInfoException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var userDao: UserDao

    fun hasUserByUserName(username: String): Boolean {
        queryByUserName(username)
        return true
    }

    fun queryByUserName(username: String): User {
        return userDao.query(username) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.NOT_USER)
    }

    fun queryByMiniProgram(): List<User> {
        return userDao.queryByMiniProgram()
    }

    fun insertUser(user: User): User {
        return userDao.insert(user) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.CREATE_USER_ERROR)
    }

    fun deleteByUserName(username: String): Boolean {
        return userDao.delete(username)
    }

}