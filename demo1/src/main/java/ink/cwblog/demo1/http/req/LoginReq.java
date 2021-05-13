package ink.cwblog.demo1.http.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chenw
 * @date 2021/5/13 14:06
 *
 * 登录请求
 */
@Data
public class LoginReq {

    /**
     * 手机号
     */
    @NotBlank(message = "手机号码不能为空")
    private String tel;
    /**
     * 密码
     */
    @NotBlank(message = "用户密码不能为空")
    private String password;
}
