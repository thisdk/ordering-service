package io.thisdk.github.ordering.service

import io.thisdk.github.ordering.bean.Food
import io.thisdk.github.ordering.dto.req.IdReq
import io.thisdk.github.ordering.dao.FoodDao
import io.thisdk.github.ordering.dao.UserDao
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import io.thisdk.github.ordering.exception.OrderingErrorInfoException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class FoodService {

    @Autowired
    lateinit var foodDao: FoodDao

    @Autowired
    lateinit var userDao: UserDao

    fun queryFoodList(): List<Food> {
        return foodDao.query()
    }

    fun updateFood(food: Food): Food {
        food.updateTime = Date().time
        return foodDao.insert(food)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.INSERT_FOOD_ERROR)
    }

    fun insertFoodList(username: String, list: List<Food>) {
        val user = userDao.queryByUsername(username)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.USER_NOT_EXIST)
        val now = Date().time
        list.forEach {
            it.createor = user.id
            it.createTime = now
            it.updateTime = now
            foodDao.insert(it)
        }
    }

    fun deleteFood(idReq: IdReq): Boolean {
        return foodDao.delete(idReq.id)
    }

}