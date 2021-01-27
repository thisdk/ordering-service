package io.thisdk.github.ordering.exception

class OrderingErrorInfoException(val code: Int, val msg: String) : RuntimeException(msg) {

    constructor(errorInfo: ErrorInfoInf) : this(errorInfo.getErrorCode(), errorInfo.getErrorMsg())

}