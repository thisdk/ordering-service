package io.thisdk.github.ordering.bean

import java.io.Serializable

data class OrderFood(
    val foodId: String,
    val foodName: String,
    val thumb: String,
    val price: Int,
    val quantity:Int,
) : Serializable
