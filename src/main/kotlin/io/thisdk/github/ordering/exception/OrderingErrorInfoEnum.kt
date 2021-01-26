package io.thisdk.github.ordering.exception

enum class OrderingErrorInfoEnum(
    private val code: Int,
    private val msg: String
) : ErrorInfoInf {

    SUCCESS(0, "OK"),
    ERROR(1, "服务器异常"),
    PARAM_ERROR(-1000, "请求参数错误"),
    DATA_NULL(-1001, "返回对象为空"),
    LIST_EMPTY(-1002, "返回列表为空"),

    USER_NOT_EXIST(-2000, "用户不存在"),
    INSERT_ORDER_ERROR(-2001, "创建订单失败"),
    INSERT_FOOD_ERROR(-2002, "创建餐品失败"),

    AUTH_ERROR(-10086, "token timeout"),
    ;

    override fun getErrorCode(): Int = code

    override fun getErrorMsg(): String = msg

}