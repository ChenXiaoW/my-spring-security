package ink.cwblog.demo1.vo;

import ink.cwblog.demo1.exception.BusinessException;
import lombok.Data;

/**
 *
 * @author other
 * @date 2021/4/1 16:10
 */
@Data
public class Response<T> {
    private Integer code;
    private String msg;
    private T data;

    public Response() {}

    public Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(SuccessResult.SUCCESS.getCode(), SuccessResult.SUCCESS.getMsg(), data);
    }

    public static <T> Response<T> unauthorized(String msg) {
        return new Response<>(ClientError.UNAUTHORIZED.getCode(), msg, null);
    }

    public static <T> Response<T> fail(ResultCode resultCode) {
        return new Response<>(resultCode.getCode(), resultCode.getMsg(),null);
    }

    public static <T> Response<T> fail(ResultCode resultCode, String msg) {
        return new Response<>(resultCode.getCode(), msg, null);
    }

    public static <T> Response<T> fail(BusinessException e) {
        return new Response<>(e.getCode(),e.getMessage(),null);
    }

}
