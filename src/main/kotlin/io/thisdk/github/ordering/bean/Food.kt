package io.thisdk.github.ordering.bean

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "wechat_food")
data class Food(
    val category: String,
    val title: String,
    val origin: Int,
    val price: Int,
) {

    @Id
    lateinit var id: String

    var tag: String? = null

    var desc: String? = null

    var time: Array<Int>? = null

    var thumb: String? = null

}
