package io.thisdk.github.ordering.bean

import io.thisdk.github.ordering.exception.ErrorInfoInf

data class RestResponse<T>(
    val code: Int,
    val data: T?,
    val msg: String
) {

    constructor(data: T) : this(0, data, "OK")

    constructor(code: Int, msg: String) : this(code, null, msg)

    constructor(error: ErrorInfoInf) : this(error.getErrorCode(), null, error.getErrorMsg())

}
