package ink.cwblog.demo1.exception;


import ink.cwblog.demo1.vo.ServerError;

/**
 * @author daihaoran
 *业务异常，需要描述具体问题，将直接抛出到用户端
 */
public class BusinessException extends RuntimeException {
    private int code;

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
        code = ServerError.BUSINESS_ERROR.getCode();
    }

    public int getCode() {
        return code;
    }
}
