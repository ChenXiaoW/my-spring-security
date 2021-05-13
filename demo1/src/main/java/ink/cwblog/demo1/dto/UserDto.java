package ink.cwblog.demo1.dto;

import ink.cwblog.demo1.pojo.Role;
import ink.cwblog.demo1.pojo.User;
import lombok.Data;

import java.util.List;

/**
 * @author chenw
 * @date 2021/5/13 10:29
 */
@Data
public class UserDto extends User {

    /**
     * 角色列表
     */
    private List<Role> roles;
}
