
package com.wx.testsecurity15.config;

import com.wx.testsecurity15.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/index").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/blogs/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login-error")
                .and()
                .exceptionHandling().accessDeniedPage("/401");
        http.logout().logoutSuccessUrl("/");
    }
    // @formatter:on

    // @formatter:off
    @Autowired
    private UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //通过AuthenticationManagerBuilder在内存中创建了一个认证用户信息，
        //用户名为IronMan,密码为123456，有USER的角色
        /*auth.inMemoryAuthentication()
                .withUser("wang").password("123456").roles("USER");*/
        //auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("wang").password("123456").roles("ADMIN");
        auth.userDetailsService(userService);
        //auth.userDetailsService(userDetailsService);
    }
    // @formatter:on


	/*@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(); // 在内存中存放用户信息
		manager.createUser(User.withUsername("wang").password("123456").roles("USER").build());
		manager.createUser(User.withUsername("admin").password("123456").roles("USER","ADMIN").build());
		return manager;
	}*/
}
