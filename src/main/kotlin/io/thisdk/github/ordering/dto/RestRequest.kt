package io.thisdk.github.ordering.dto

data class RestRequest<T>(
    val param: T,
    val client: String,
    val timestamp: String
)
