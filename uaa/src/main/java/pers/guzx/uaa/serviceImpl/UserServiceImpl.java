package pers.guzx.uaa.serviceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pers.guzx.uaa.mapper.UserMapper;
import pers.guzx.uaa.entity.SysUserDetails;
import pers.guzx.uaa.service.UserService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/19 10:22
 * @describe
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails getUserByUsername(String username) {
        Example example = new Example(SysUserDetails.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        List<SysUserDetails> sysUserDetails = userMapper.selectByExample(example);
        if (sysUserDetails.size() > 0) {
            return sysUserDetails.get(0);
        }
        return null;
    }

    public SysUserDetails saveUser(SysUserDetails user){
        userMapper.insert(user);
        return user;
    }
}
