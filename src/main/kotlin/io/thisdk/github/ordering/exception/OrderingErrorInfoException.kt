package io.thisdk.github.ordering.exception

class OrderingErrorInfoException(val code: Int, val msg: String) : RuntimeException("error code $code msg $msg") {

    constructor(errorInfo: ErrorInfoInf) : this(errorInfo.getErrorCode(), errorInfo.getErrorMsg())

}