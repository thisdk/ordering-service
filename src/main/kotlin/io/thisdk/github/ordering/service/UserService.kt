package io.thisdk.github.ordering.service

import io.thisdk.github.ordering.bean.QueryUserReq
import io.thisdk.github.ordering.bean.User
import io.thisdk.github.ordering.dao.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var userDao: UserDao

    fun getUserInfo(req: QueryUserReq): User? {
        return userDao.query(req.openid)
    }

    fun insertUser(user: User): User? {
        return userDao.insert(user)
    }

    fun deleteAllUser(){
        userDao.deleteAll()
    }

}