package pers.guzx.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/23 14:00
 * @describe 认证授权服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private AuthorizationCodeServices authorizationCodeServices;

    @Resource
    private ClientDetailsService clientDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private TokenStore tokenStore;
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 配置令牌端点的安全约束
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 开启 表单认证申请令牌
        security.allowFormAuthenticationForClients()
                // 开启 /oauth/token_key 访问
                .tokenKeyAccess("permitAll()")
                // 开启 /oauth/check_token 访问
                .checkTokenAccess("permitAll()");
    }

    /**
     * 配置客户端详细信息(ClientDetailsService)
     * ClientDetailsServiceConfigurer能够使用内存或者JDBC来实现客户端详情服务(ClientDetailsService)，
     * ClientDetailsService负责查找ClientDetails，一个ClientDetails代表一个需要接入的第三方应用
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("c1")
                .secret(passwordEncoder.encode("secret"))
                .resourceIds("resources")
                .authorizedGrantTypes("authorization_code", "implicit", "password", "client_credentials", "refresh_token")
                .accessTokenValiditySeconds(3600)
                .scopes("all")
                .autoApprove(false)
                .redirectUris("https://www.baidu.com")
                .and()
                .withClient("c2")
                .secret(passwordEncoder.encode("secret2"))
                .resourceIds("resources")
                .authorizedGrantTypes("authorization_code", "implicit", "password", "client_credentials", "refresh_token")
                .accessTokenValiditySeconds(3600)
                .scopes("query")
                .autoApprove(false)
                .redirectUris("https://www.baidu.com");

        // 自定义客户端管理服务
//        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 配置令牌端点以及令牌服务(tokenServices)
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // token存储位置
        endpoints
                .tokenStore(tokenStore)
                // 认证管理器
                .authenticationManager(authenticationManager)
                // 密码模式的用户信息管理
                .userDetailsService(userDetailsService)
                // 授权码模式
                .authorizationCodeServices(authorizationCodeServices)
                // 令牌管理服务
                .tokenServices(tokenServices())
                // 允许访问端点的方法
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }


    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        tokenServices.setTokenEnhancer(tokenEnhancerChain);

        tokenServices.setAccessTokenValiditySeconds(600);
        tokenServices.setRefreshTokenValiditySeconds(36000);
        return tokenServices;
    }

}
