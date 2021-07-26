package pers.guzx.uaa.entity;

import lombok.Data;
import pers.guzx.uaa.code.ErrorCode;

import java.io.Serializable;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/2 17:03
 * @describe
 */
@Data
public class JsonDto<T> implements Serializable {

    private static final int SUCCESS_CODE = 200;
    private static final String SUCCESS_MSG = "SUCCESS";

    private int code = SUCCESS_CODE;
    private String message = SUCCESS_MSG;
    private T data = null;

    /**
     * 是否正常的响应
     *
     * @return true为正常返回
     */
    public boolean isOk() {
        return code == SUCCESS_CODE;
    }

    /**
     * 无data的正常返回
     *
     * @return 成功信息
     */
    public static <T> JsonDto<T> retOk() {
        return new JsonDto<>();
    }

    /**
     * 有data的正常返回
     *
     * @param data 正确信息
     * @param <T>  信息类型
     * @return 正确信息
     */
    public static <T> JsonDto<T> retOk(T data) {
        JsonDto<T> response = new JsonDto<>();
        response.setData(data);
        return response;
    }

    /**
     * 无data的失败返回
     *
     * @param error 错误类型
     * @return 失败信息
     */
    public static <T> JsonDto<T> retFail(ErrorCode error) {
        return retFail(error.getCode(), error.getMsg());
    }

    /**
     * 有data的失败返回
     *
     * @param error 错误类型
     * @param data  详细错误信息
     * @param <T>   泛型
     * @return 失败信息
     */
    public static <T> JsonDto<T> retFail(ErrorCode error, T data) {
        return retFail(error.getCode(), error.getMsg(), data);
    }

    /**
     * 无data的失败返回
     *
     * @param code 错误码
     * @param msg  错误信息
     * @param <T>  泛型
     * @return 失败信息
     */
    public static <T> JsonDto<T> retFail(int code, String msg) {
        JsonDto<T> response = new JsonDto<>();
        response.setCode(code);
        response.setMessage(msg);
        return response;
    }

    /**
     * 有data的失败返回
     *
     * @param code 错误码
     * @param msg  错误信息
     * @param data 实际对象
     * @param <T>  实际类型
     * @return 失败信息
     */
    public static <T> JsonDto<T> retFail(int code, String msg, T data) {
        JsonDto<T> response = new JsonDto<>();
        response.setCode(code);
        response.setMessage(msg);
        response.setData(data);
        return response;
    }
}
