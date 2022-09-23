package cn.hwq.security;

import cn.hwq.pojo.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private Customer customer;


    //设置权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //设置自己的账号密码,以便security框架的调用
    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    //设置自己的账号密码,以便security框架的调用
    @Override
    public String getUsername() {
        return customer.getAccount();
    }


    //控制帐户状态是否为未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //控制帐户状态是否为未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //控制用户可用时间是否没有超时
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //控制用户状态是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}