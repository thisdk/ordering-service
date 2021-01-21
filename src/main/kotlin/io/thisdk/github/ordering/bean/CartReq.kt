package io.thisdk.github.ordering.bean

data class CartReq(
    val openid: String,
    val total: Int,
    val quantity:Int,
    val list: List<CartItemReq>
)
