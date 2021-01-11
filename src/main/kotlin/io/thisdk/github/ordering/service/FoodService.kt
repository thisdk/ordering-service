package io.thisdk.github.ordering.service

import io.thisdk.github.ordering.bean.Food
import io.thisdk.github.ordering.bean.IdReq
import io.thisdk.github.ordering.dao.FoodDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FoodService {

    @Autowired
    lateinit var foodDao: FoodDao

    fun getFoodList(): List<Food>? {
        return foodDao.query()
    }

    fun insertFood(food: Food): Food? {
        return foodDao.insert(food)
    }

    fun deleteFood(idReq: IdReq) {
        foodDao.delete(idReq.id)
    }

}