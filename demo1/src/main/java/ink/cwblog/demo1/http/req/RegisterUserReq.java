package ink.cwblog.demo1.http.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chenw
 * @date 2021/5/13 11:51
 *
 * 用户注册
 */
@Data
public class RegisterUserReq {

    /**
     * 手机号
     */
    @NotBlank(message = "手机号码不能为空")
    private String tel;
    /**
     * 名称
     */
    @NotBlank(message = "用户昵称不能为空")
    private String name;
    /**
     * 密码
     */
    @NotBlank(message = "用户密码不能为空")
    private String password;
}
