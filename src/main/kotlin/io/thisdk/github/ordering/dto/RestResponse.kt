package io.thisdk.github.ordering.dto

import io.thisdk.github.ordering.exception.ErrorInfoInf
import java.io.Serializable

data class RestResponse<T>(
    val code: Int,
    val data: T?,
    val msg: String
) : Serializable {

    constructor() : this(0, null, "OK")

    constructor(data: T) : this(0, data, "OK")

    constructor(code: Int, msg: String) : this(code, null, msg)

    constructor(error: ErrorInfoInf) : this(error.getErrorCode(), null, error.getErrorMsg())

}
