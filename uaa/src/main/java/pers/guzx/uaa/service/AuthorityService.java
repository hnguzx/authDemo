package pers.guzx.uaa.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pers.guzx.uaa.entity.UserAuthority;

import java.util.List;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/19 11:35
 * @describe
 */
public interface AuthorityService {
    UserAuthority getUserAuthority(Integer userId);

    List<GrantedAuthority> getAuthorityById(List<UserAuthority> userAuthorities);

    List<UserAuthority> getUserAuthorityByUser(UserDetails user);
}
