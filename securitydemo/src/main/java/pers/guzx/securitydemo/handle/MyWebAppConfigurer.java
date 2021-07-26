package pers.guzx.securitydemo.handle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/16 15:49
 * @describe
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    /*@Resource
    DataSource dataSource;*/

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(
                User.withUsername("admin").password(passwordEncoder().encode("admin")).
                        authorities("mobile", "salary").build(),
                User.withUsername("manager").password(passwordEncoder().encode("manager")).
                        authorities("salary").build(),
                User.withUsername("worker").password(passwordEncoder().encode("worker")).
                        authorities("worker").build());
        return userDetailsManager;

        /*JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        return userDetailsManager;*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
