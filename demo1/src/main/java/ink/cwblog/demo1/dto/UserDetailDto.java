package ink.cwblog.demo1.dto;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import ink.cwblog.demo1.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 认证信息
 * @author chenw
 * @date 2021/5/13 10:19
 */
public class UserDetailDto implements UserDetails {
    /**
     * 用户信息
     */
    private UserDto userDto;

    public UserDetailDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        for (int i = 0 ;i < userDto.getRoles().size() ; i++) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userDto.getRoles().get(i).getName()));
        }
        return authorities;
    }

    public Integer getId() {
        return userDto.getId();
    }

    public User getUser() {
        return userDto;
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return ObjectUtils.isEmpty(userDto.getDeleteTime());
    }

    @Override
    public String toString() {
        return "UserDetailDto{" +
                "user=" + userDto +
                '}';
    }
}
