package io.thisdk.github.ordering.exception

enum class OrderingErrorInfoEnum(
    private val code: Int,
    private val msg: String
) : ErrorInfoInf {

    SUCCESS(0, "OK"),
    ERROR(-1, "服务器异常"),

    PARAM_ERROR(-1000, "请求参数错误"),
    DATA_NULL(-1001, "返回对象为空"),
    LIST_EMPTY(-1002, "返回列表为空"),

    USER_NOT_EXIST(-2000, "用户不存在"),
    USER_EXIST(-2001, "用户已存在"),
    CREATE_USER_ERROR(-2002, "创建用户异常"),

    OBTAIN_NOT_ORDER(-3000, "查询不到该订单"),
    OBTAIN_ERROR(-3001, "取餐出现异常"),
    OBTAIN_STATUS_ERROR(-3003, "该订单无法再取餐"),

    INSERT_ORDER_ERROR(-4000, "创建订单失败"),
    INSERT_FOOD_ERROR(-4001, "创建餐品失败"),


    LOGIN_ERROR(-1024, "验证用户名或密码失败"),
    ACCESS_DENIED_ERROR(-2048, "账号权限不允许访问"),
    AUTH_ERROR(-10086, "unAuthorization"),
    ;

    override fun getErrorCode(): Int = code

    override fun getErrorMsg(): String = msg

}