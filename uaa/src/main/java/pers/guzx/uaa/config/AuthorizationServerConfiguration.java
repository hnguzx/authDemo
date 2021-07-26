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
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/23 14:00
 * @describe 认证授权服务器配置
 */
    /*
    -- used in tests that use HSQL
    create table oauth_client_details (
      client_id VARCHAR(128) PRIMARY KEY,
      resource_ids VARCHAR(128),
      client_secret VARCHAR(128),
      scope VARCHAR(128),
      authorized_grant_types VARCHAR(128),
      web_server_redirect_uri VARCHAR(128),
      authorities VARCHAR(128),
      access_token_validity INTEGER,
      refresh_token_validity INTEGER,
      additional_information VARCHAR(4096),
      autoapprove VARCHAR(128)
    );
    create table oauth_client_token (
      token_id VARCHAR(128),
      token BLOB,
      authentication_id VARCHAR(128) PRIMARY KEY,
      user_name VARCHAR(128),
      client_id VARCHAR(128)
    );
    create table oauth_access_token (
      token_id VARCHAR(128),
      token BLOB,
      authentication_id VARCHAR(128) PRIMARY KEY,
      user_name VARCHAR(128),
      client_id VARCHAR(128),
      authentication BLOB,
      refresh_token VARCHAR(128)
    );
    create table oauth_refresh_token (
      token_id VARCHAR(128),
      token BLOB,
      authentication BLOB
    );
    create table oauth_code (
      code VARCHAR(128), authentication BLOB
    );
    create table oauth_approvals (
        userId VARCHAR(128),
        clientId VARCHAR(128),
        scope VARCHAR(128),
        status VARCHAR(10),
        expiresAt TIMESTAMP,
        lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    -- customized oauth_client_details table
    create table ClientDetails (
      appId VARCHAR(128) PRIMARY KEY,
      resourceIds VARCHAR(128),
      appSecret VARCHAR(128),
      scope VARCHAR(128),
      grantTypes VARCHAR(128),
      redirectUrl VARCHAR(128),
      authorities VARCHAR(128),
      access_token_validity INTEGER,
      refresh_token_validity INTEGER,
      additionalInformation VARCHAR(4096),
      autoApproveScopes VARCHAR(128)
    );*/
        /*
    INSERT INTO `oauth_client_details` VALUES ('client1', 'resource1', '$2a$10$YEpRG0cFXz5yfC/lKoCHJ.83r/K3vaXLas5zCeLc.EJsQ/gL5Jvum', 'scope1,scope2', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://www.baidu.com', null, '300', '1500', null, 'false');*/

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

    @Resource
    private DataSource dataSource;

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
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);

        clients.withClientDetails(clientDetailsService);
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
