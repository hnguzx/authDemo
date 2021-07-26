package pers.guzx.uaa.entity;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/19 10:02
 * @describe 系统用户 一个用户在系统中只有一个角色，多个权限
 */
@Setter
@Table(name = "sys_user")
public class SysUserDetails implements UserDetails, Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String username;
    private String password;

    @Column(name = "account_expired")
    private Boolean accountNonExpired;
    @Column(name = "account_locked")
    private Boolean accountNonLocked;
    @Column(name = "credentials_expired")
    private Boolean credentialsNonExpired;
    @Column(name = "enabled")
    private Boolean enabled;

    private List<GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
