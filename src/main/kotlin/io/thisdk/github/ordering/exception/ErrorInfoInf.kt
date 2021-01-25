package io.thisdk.github.ordering.exception

interface ErrorInfoInf {
    fun getErrorCode(): Int
    fun getErrorMsg(): String
}