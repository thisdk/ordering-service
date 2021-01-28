package io.thisdk.github.ordering.bean

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "wechat_order")
data class Order(
    val openid: String,
    val nickName: String,
    val phone: String,
    val remark: String,
    val createTime: Date,
    // 0 未付款 1 已付款 2 已取餐 3 已取消
    var status: Int,
    val orderPrice: Int,
    val amountPrice: Int,
    val refundPrice: Int,
    val quantity: Int,
    val code: String,
    val list: List<OrderFood>
) {
    @Id
    lateinit var id: String

    @Indexed(unique = true)
    lateinit var orderId: String

    var payId: String? = null

    var payTime: Date? = null

    var obtainTime: Date? = null

}
