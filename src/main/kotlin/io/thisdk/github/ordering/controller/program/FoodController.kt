package io.thisdk.github.ordering.controller.program

import io.thisdk.github.ordering.bean.Food
import io.thisdk.github.ordering.bean.IdReq
import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.redis.RedisConfig
import io.thisdk.github.ordering.service.FoodService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/program/food")
class FoodController {

    @Autowired
    lateinit var foodService: FoodService

    @RequestMapping("/insert")
    fun insert(@RequestBody req: RestRequest<Food>): RestResponse<Unit> {
        foodService.insertFoodList(arrayListOf(req.param))
        return RestResponse()
    }

    @RequestMapping("/inserts")
    fun inserts(@RequestBody req: RestRequest<List<Food>>): RestResponse<Unit> {
        foodService.insertFoodList(req.param)
        return RestResponse()
    }

    @RequestMapping("/update")
    fun update(@RequestBody req: RestRequest<Food>): RestResponse<Food> {
        return RestResponse(foodService.updateFood(req.param))
    }

    @Cacheable(cacheNames = [RedisConfig.REDIS_KEY_DATABASE], key = "'food-controller-query'")
    @RequestMapping("/query")
    fun query(@RequestBody req: RestRequest<Unit>): RestResponse<List<Food>> {
        return RestResponse(foodService.queryFoodList())
    }

    @RequestMapping("/delete")
    fun delete(@RequestBody req: RestRequest<IdReq>): RestResponse<Boolean> {
        return RestResponse(foodService.deleteFood(req.param))
    }

}