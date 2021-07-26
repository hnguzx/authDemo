package pers.guzx.uaa.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/19 10:34
 * @describe
 */
@Data
@Table(name = "sys_role_authority")
public class RoleAuthority implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private Integer roleId;
    private Integer authorityId;
}
