package io.thisdk.github.ordering.uuid;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 手机访问后返回的信息头,每一个dto对象须包含
 */
public class HeadDto implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int               ret;
    private String msg;
    private String uri;
    private String method;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp        = new Date();
    private String requestId;
    private Object param;

    public HeadDto() {
    }

    public HeadDto(int ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
