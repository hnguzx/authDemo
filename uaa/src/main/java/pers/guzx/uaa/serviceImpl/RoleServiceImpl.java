package pers.guzx.uaa.serviceImpl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pers.guzx.uaa.entity.SysRole;
import pers.guzx.uaa.entity.SysUserDetails;
import pers.guzx.uaa.entity.UserRole;
import pers.guzx.uaa.mapper.RoleMapper;
import pers.guzx.uaa.mapper.UserRoleMapper;
import pers.guzx.uaa.service.RoleService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/19 11:36
 * @describe
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public UserRole getUserRole(Integer userId) {
        return null;
    }

    @Override
    public GrantedAuthority getRoleById(UserRole userRole) {
        SysRole role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
        return role;
    }

    @Override
    public UserRole getUserRoleByUser(UserDetails user) {
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", ((SysUserDetails) user).getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        if (userRoles.size() > 0) {
            return userRoles.get(0);
        }
        return null;
    }

    public SysRole save(SysRole role) {
        int insert = roleMapper.insert(role);
        return role;
    }

    public UserRole save(UserRole userRole) {
        int insert = userRoleMapper.insert(userRole);
        return userRole;
    }
}
