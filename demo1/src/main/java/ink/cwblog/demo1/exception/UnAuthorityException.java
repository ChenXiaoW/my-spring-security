package ink.cwblog.demo1.exception;


import ink.cwblog.demo1.vo.ClientError;

/**
 * @author chenw
 * @date 2021/5/7 10:00
 * 无权限异常
 *
 */
public class UnAuthorityException extends RuntimeException{
    private int code;


    public UnAuthorityException(String message) {
        super(message);
        code = ClientError.FORBIDDEN.getCode();
    }

    public int getCode() {
        return code;
    }
}
