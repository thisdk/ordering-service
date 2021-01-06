package io.thisdk.github.ordering.controller.program

import io.thisdk.github.ordering.bean.DeleteFoodReq
import io.thisdk.github.ordering.bean.Food
import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.service.FoodService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/food")
class FoodController {

    @Autowired
    lateinit var foodService: FoodService

    @PostMapping("/insert")
    fun insert(@RequestBody req: RestRequest<Food>): RestResponse<Food> {
        return RestResponse(foodService.insertFood(req.param))
    }

    @RequestMapping("/query")
    fun query(@RequestBody req: RestRequest<Unit>): RestResponse<List<Food>> {
        return RestResponse(data = foodService.getFoodList())
    }

    @RequestMapping("/delete")
    fun delete(@RequestBody req: RestRequest<DeleteFoodReq>): RestResponse<Unit> {
        return RestResponse(foodService.deleteFood(req.param.id))
    }

}