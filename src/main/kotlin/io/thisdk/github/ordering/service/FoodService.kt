package io.thisdk.github.ordering.service

import io.thisdk.github.ordering.bean.Food
import io.thisdk.github.ordering.bean.IdReq
import io.thisdk.github.ordering.dao.FoodDao
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import io.thisdk.github.ordering.exception.OrderingErrorInfoException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FoodService {

    @Autowired
    lateinit var foodDao: FoodDao

    fun queryFoodList(): List<Food> {
        return foodDao.query()
    }

    fun updateFood(food: Food): Food {
        return foodDao.insert(food)
            ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.INSERT_FOOD_ERROR)
    }

    fun insertFoodList(list: List<Food>) {
        list.forEach {
            foodDao.insert(it)
        }
    }

    fun deleteFood(idReq: IdReq): Boolean {
        return foodDao.delete(idReq.id)
    }

}