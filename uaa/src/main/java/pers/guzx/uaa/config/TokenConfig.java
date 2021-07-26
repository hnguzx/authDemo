package pers.guzx.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/26 14:44
 * @describe
 */
@Configuration
public class TokenConfig {

    private static final String SIGN_KEY = "uaa";

    @Resource
    private DataSource dataSource;

    @Primary
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(tokenConverter());
//        return new JwtTokenStore(tokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(SIGN_KEY);
        return tokenConverter;
    }
}
