package io.thisdk.github.ordering.dao

import io.thisdk.github.ordering.bean.Food

interface FoodDao {

    fun insert(food: Food): Food?

    fun delete(id: String)

    fun query(): List<Food>?

    fun query(id:String): Food?

}