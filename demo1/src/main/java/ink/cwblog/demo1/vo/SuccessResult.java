package ink.cwblog.demo1.vo;

/**
 * @author other
 * @date 2021/4/1 15:38
 */
public enum SuccessResult implements ResultCode {
    SUCCESS(200, "ok");

    private int code;
    private String msg;

    SuccessResult(int code, String msg) {
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