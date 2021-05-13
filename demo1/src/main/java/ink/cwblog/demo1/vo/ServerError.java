package ink.cwblog.demo1.vo;
/**
 * 服务端异常
 * @author other
 * @date 2021/4/1 17:42
 */
public enum ServerError implements ResultCode{

    SERVER_ERROR(500, "internal server error"),
    BUSINESS_ERROR(511, "business error"),
    OSS_ERROR(521,"oss server error"),
    WX_ERROR(522,"wxChat api call error");

    private int code;
    private String msg;

    private ServerError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() { return code;}
    @Override
    public String getMsg() { return msg;}
}
