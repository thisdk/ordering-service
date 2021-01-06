package io.thisdk.github.ordering.bean

data class RestResponse<T>(
    val data: T?,
    val code: Int = 0,
    val msg: String = "OK"
) {

    override fun toString(): String {
        return "RestResponse(data=$data, code=$code, msg='$msg')"
    }

}
