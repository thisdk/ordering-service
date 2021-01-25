package io.thisdk.github.ordering.bean

data class RestRequest<T>(
    val param: T,
    val client: String,
    val timestamp: String
)
