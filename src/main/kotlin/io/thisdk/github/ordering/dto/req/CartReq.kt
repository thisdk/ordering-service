package io.thisdk.github.ordering.dto.req

data class CartReq(
    val openid: String,
    val total: Int,
    val quantity:Int,
    val list: List<CartItemReq>
){
    var phone: String = ""
    var remark: String = ""
}
