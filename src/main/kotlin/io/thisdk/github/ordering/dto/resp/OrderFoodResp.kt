package io.thisdk.github.ordering.dto.resp

import java.io.Serializable

data class OrderFoodResp(
    val foodId: String,
    val foodName: String,
    val thumb: String,
    val price: Int,
    val quantity:Int,
) : Serializable
