package pers.guzx.basicauth.handle;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/16 15:49
 * @describe
 */
@Component
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Resource
    private AuthInterceptor authInterceptor;

    //配置权限拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }
}
