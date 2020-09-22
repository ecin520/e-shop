package com.pytap.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * oauth2认证中心配置
 *
 * @author Ecin520
 * @date 2020/8/16 14:48
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsService userDetailsService;


    /**
     * oauth2的验证方式常用的有两种，授权码模式与密码模式，这里使用授权码模式和密码模式结合，
     * 配置客户端的信息，可从数据库获取，在这里因为不向外界提供，因此写死，刷新token有效期为一个月，过期后重新认证
     * withClient -> client_id
     * authorizedGrantTypes -> grant_type
     * scope -> scope 这是资源访问权限，因为这是自己的客户端，因此可以授予all权限
     * secret -> client_secret
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("e-shop-client")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .scopes("all")
                .secret(passwordEncoder.encode("e-shop"))
                .accessTokenValiditySeconds(60 * 60 * 24)
                .refreshTokenValiditySeconds(60 * 60 * 24 * 30);
    }

    /**
     * 用来配置授权、token的访问端点和令牌服务以及token的存储方式tokenStore
     * 允许GET与POST提交
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    /**
     * 允许表单认证
     * */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 使用redis存储token
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
}
