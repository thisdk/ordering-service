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

    fun queryByUserId(id: String): User {
        return userDao.queryByUserId(id) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.USER_NOT_EXIST)
    }

    fun hasUserByUserName(username: String): Boolean {
        try {
            queryByUserName(username)
        } catch (exception: OrderingErrorInfoException) {
            exception.printStackTrace()
            return false
        }
        return true
    }

    fun queryByUserName(username: String): User {
        return userDao.queryByUsername(username)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.USER_NOT_EXIST)
    }

    fun queryAllMiniProgramUser(): List<User> {
        return userDao.queryAllMiniProgramUser()
    }

    fun insertUser(user: User): User {
        return userDao.insert(user) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.CREATE_USER_ERROR)
    }

    fun deleteByUserName(username: String): Boolean {
        return userDao.deleteByUsername(username)
    }

}