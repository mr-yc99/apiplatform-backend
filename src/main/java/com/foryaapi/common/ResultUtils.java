package com.foryaapi.common;

/**
 * 返回工具类
 *
 * @author foryaapi
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> com.foryaapi.common.BaseResponse<T> success(T data) {
        return new com.foryaapi.common.BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static com.foryaapi.common.BaseResponse error(com.foryaapi.common.ErrorCode errorCode) {
        return new com.foryaapi.common.BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @return
     */
    public static com.foryaapi.common.BaseResponse error(int code, String message) {
        return new com.foryaapi.common.BaseResponse(code, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static com.foryaapi.common.BaseResponse error(com.foryaapi.common.ErrorCode errorCode, String message) {
        return new com.foryaapi.common.BaseResponse(errorCode.getCode(), null, message);
    }
}
