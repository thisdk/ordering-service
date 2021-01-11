package io.thisdk.github.ordering.bean

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "wechat_order_food")
data class OrderFood(
    @Indexed val orderId: String,
    @Indexed val foodId: String,
    val foodName: String,
    val thumb: String,
    val price: Int,
    val quantity:Int,
){
    @Id
    lateinit var id: String
}
