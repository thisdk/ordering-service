package io.thisdk.github.ordering.enums;

/**
 * @author jay
 */

public enum ExceptionEnums implements IEnums {

    SUCCESS(0, "成功"),
    ERROR(1, "错误"),;


    private final int code;
    private final String message;

    /**
     * Constructor.
     */
    private ExceptionEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Get the value.
     *
     * @return the value
     */
    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
