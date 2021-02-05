package io.thisdk.github.ordering.controller.program

import io.thisdk.github.ordering.bean.Food
import io.thisdk.github.ordering.bean.IdReq
import io.thisdk.github.ordering.bean.RestRequest
import io.thisdk.github.ordering.bean.RestResponse
import io.thisdk.github.ordering.exception.OrderingErrorInfoEnum
import io.thisdk.github.ordering.exception.OrderingErrorInfoException
import io.thisdk.github.ordering.jwt.JwtUtils
import io.thisdk.github.ordering.redis.RedisConfig
import io.thisdk.github.ordering.service.FoodService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@CrossOrigin
@RequestMapping("/program/food")
class FoodController {

    companion object {
        private val logger = LoggerFactory.getLogger(FoodController::class.java)
    }

    @Value("\${token.jwt.header}")
    lateinit var jwtHeader: String

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @Autowired
    lateinit var foodService: FoodService

    @RequestMapping("/insert")
    @PreAuthorize("hasRole('ADMIN')")
    fun insert(@RequestBody req: RestRequest<Food>, request: HttpServletRequest): RestResponse<Unit> {
        val token = request.getHeader(jwtHeader) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.ERROR)
        val username = jwtUtils.getUserNameFromJwtToken(token)
        logger.info("FoodController insert food : {}", req.param)
        foodService.insertFoodList(username, arrayListOf(req.param))
        return RestResponse()
    }

    @RequestMapping("/inserts")
    @PreAuthorize("hasRole('ADMIN')")
    fun inserts(@RequestBody req: RestRequest<List<Food>>, request: HttpServletRequest): RestResponse<Unit> {
        val token = request.getHeader(jwtHeader) ?: throw OrderingErrorInfoException(OrderingErrorInfoEnum.ERROR)
        val username = jwtUtils.getUserNameFromJwtToken(token)
        logger.info("FoodController inserts food : {}", req.param)
        foodService.insertFoodList(username, req.param)
        return RestResponse()
    }

    @RequestMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@RequestBody req: RestRequest<Food>): RestResponse<Food> {
        logger.info("FoodController update food : {}", req.param)
        return RestResponse(foodService.updateFood(req.param))
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    fun delete(@RequestBody req: RestRequest<IdReq>): RestResponse<Boolean> {
        logger.info("FoodController delete food : {}", req.param)
        return RestResponse(foodService.deleteFood(req.param))
    }

    @Cacheable(cacheNames = [RedisConfig.REDIS_KEY_DATABASE], key = "'food-controller-query'")
    @RequestMapping("/query")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    fun query(@RequestBody req: RestRequest<Unit>): RestResponse<List<Food>> {
        val foods = foodService.queryFoodList()
        logger.info("FoodController query food : {}", foods)
        return RestResponse(foods)
    }

}