package io.thisdk.github.ordering.bean

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "wechat_order")
data class Order(
    @Indexed(unique = true) val orderId: String,
    @Indexed(unique = true) val payId: String,
    val openid: String,
    val nickName: String,
    val phone: String,
    val remark: String,
    val createTime: Date,
    val payTime: Date,
    val status: Int,
    val orderPrice: Int,
    val amountPrice: Int,
    val refundPrice: Int,
    val payType: Int,
    val code: String,
) {
    @Id
    lateinit var id: String
}