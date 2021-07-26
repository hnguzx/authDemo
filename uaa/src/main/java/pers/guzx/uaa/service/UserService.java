package pers.guzx.uaa.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/19 10:22
 * @describe
 */
public interface UserService {
    UserDetails getUserByUsername(String username);
}
