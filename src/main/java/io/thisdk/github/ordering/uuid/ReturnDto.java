package io.thisdk.github.ordering.uuid;
/**
 * 手机访问后返回的信息头,每一个dto对象须包含
 */
public class ReturnDto<T> implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 返回结果的消息头
     */
    private HeadDto           head;
    /**
     * 返回结果的消息体
     */
    private T            data;

    public ReturnDto() {
        super();
    }

    public ReturnDto(HeadDto head, T data) {
        super();
        this.head = head;
        this.data = data;
    }

    public HeadDto getHead() {
        return head;
    }

    public void setHead(HeadDto head) {
        this.head = head;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
