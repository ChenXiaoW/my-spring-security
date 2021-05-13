package ink.cwblog.demo1.vo;
/**
 * 客户端错误
 * @author chenw
 * @date 2021/4/1 15:40
 *
 *
 */
public enum ClientError implements ResultCode{

    UNAUTHORIZED(401, "not login or token expired"),
    FORBIDDEN(403, "no related permissions"),
    PARAM_ERROR(411, "param error");

    private int code;
    private String msg;

    ClientError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
