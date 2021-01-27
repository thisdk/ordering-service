package io.thisdk.github.ordering.controller.program

import io.thisdk.github.ordering.bean.Food
import io.thisdk.github.ordering.bean.IdReq
import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.service.FoodService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/program/food")
class FoodController {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var foodService: FoodService

    @PostMapping("/insert")
    fun insert(@RequestBody req: RestRequest<Food>): RestResponse<Food> {
        return RestResponse(foodService.insertFood(req.param))
    }

    @PostMapping("/update")
    fun update(@RequestBody req: RestRequest<Food>): RestResponse<Food> {
        return RestResponse(foodService.insertFood(req.param))
    }

    @PostMapping("/insertList")
    fun insertList(@RequestBody req: RestRequest<List<Food>>): RestResponse<Unit> {
        return RestResponse(foodService.insertList(req.param))
    }

    @RequestMapping("/query")
    fun query(@RequestBody req: RestRequest<Unit>): RestResponse<List<Food>> {
        return RestResponse(data = foodService.getFoodList())
    }

    @RequestMapping("/delete")
    fun delete(@RequestBody req: RestRequest<IdReq>): RestResponse<Unit> {
        return RestResponse(foodService.deleteFood(req.param))
    }

}