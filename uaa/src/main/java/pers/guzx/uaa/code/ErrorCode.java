package pers.guzx.uaa.code;

import lombok.Getter;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/2 17:08
 * @describe
 */
@Getter
public enum ErrorCode {

    /**
     * 系统错误码
     */
    OK(200, "已在响应中发出"),
    CREATED(201, "新资源被创建"),
    ACCEPTED(202, "已接受处理请求但尚未完成（异步处理）"),
    NULL(204, "请求失败"),
    MOVED_PERMANENTLY(301, "资源的URI已被更新"),
    SEE_OTHER(303, "其他（如，负载均衡）"),
    NOT_MODIFIED(304, "资源未更改（缓存）"),
    BAD_REQUEST(400, "坏请求（如，参数错误）"),
    NOT_FOUND(404, "资源不存在"),
    NOT_ACCEPTABLE(406, "服务端不支持所需表示"),
    CONFLICT(409, "通用冲突"),
    PRECONDITION_FAILED(412, "前置条件失败（如执行条件更新时的冲突）"),
    UNSUPPORTED_MEDIA_TYPE(415, " 接受到的媒体类型不受支持"),
    INTERNAL_SERVER_ERROR(500, "通用错误响应"),
    SERVICE_UNAVAILABLE(503, "服务端当前无法处理请求"),
    /**
     * 业务错误码
     */
    UPDATE_INFO_FAIL(100000, "数据更新失败！"),
    INFO_IS_LATEST(100001, "数据已是最新！"),
    DATA_EXCEPTION(100002, "数据异常！"),
    FILE_UPLOAD(100003, "文件上传失败！"),
    FILE_NOT_FOUND(100004, "文件不存在！"),
    DATA_VALIDATE(100005, "数据验证未通过！"),
    VERIFY_ERROR(100006, "验证码不匹配！"),
    VERIFY_NOT_FOUND(100007, "验证码不正确或验证码已过期！"),
    USER_NOT_FOUND(200000, "用户信息不存在！"),
    USER_INSERT_FAIL(200001, "新增用户失败！"),
    USER_INFO_EXC(200002, "用户信息异常！"),
    USER_INFO_EXIST(200003, "该邮箱或手机号已注册！"),
    EMAIL_SEND_ERROR(200004, "邮件发送错误！"),
    MSG_SEND_ERROR(200005, "邮件发送错误！"),
    USER_NOT_LOGIN(200006, "用户未登陆"),
    USER_ACCOUNT_EXPIRED(200007, "账号已过期"),
    USER_CREDENTIALS_ERROR(200008, "密码错误"),
    USER_CREDENTIALS_EXPIRED(200009, "密码已过期"),
    USER_ACCOUNT_DISABLE(200010, "账号不可用"),
    USER_ACCOUNT_LOCKED(200011, "账号已锁定"),
    USER_ACCOUNT_NOT_EXIST(200012, "用户不存在"),
    USER_ACCOUNT_USE_BY_OTHERS(200013, "用户账户已在别处登录"),
    USER_CONNECT_SUCCESS(200014, "连接成功"),
    USER_DISCONNECT_SUCCESS(200015, "断开连接成功"),
    USER_NOT_AUTH(200016, "用户没有权限"),
    CUSTOMER_AUTHORITY_FAIL(200017, "客户认证失败"),
    // 3000-3999 通知相关错误
    DIARY_NOTFOUND(300000, "日记信息不存在！"),
    // 4000-4999 好友相关错误
    FRIEND_IS_ADDED(400000, "好友已添加，请不要重复添加！"),
    // 5000-5999 通知系统错误
    MSG_EMAIL_FORMAT_ERROR(500000,"邮箱格式不正确"),
    MSG_RECEIVER_NOT_FOUND(500001,"消息接受者不能为空"),
    MSG_CONTENT_NOT_FOUND(500002,"消息内容不能为空"),
    COMMON_BUSINESS_ERROR(999999, "通用业务错误");

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCode getStateEnumById(Integer code) {
        for (ErrorCode userStateEnum : ErrorCode.values()) {
            if (code.equals(userStateEnum.getCode())) {
                return userStateEnum;
            }
        }
        return null;
    }

}
