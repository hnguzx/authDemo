package pers.guzx.uaa.entity;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/19 10:08
 * @describe 系统权限
 * 不仅可以通过角色访问，还可以直接通过用户权限表关联到用户达到访问的目的
 * 如果没有在配置类中配置可以访问本资源的权限，则所有通过认证的都可以访问
 * 可以设置一个特殊角色，然后在方法上设置需要的角色权限
 */
@Setter
public class SysAuthority implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String code;
    private String url;

    public Integer getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String getAuthority() {
        return this.url;
    }
}
