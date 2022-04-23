package com.example.spring_security_lab1.WebConfig;


import com.example.spring_security_lab1.Dto.UserDto;
import com.example.spring_security_lab1.Repository.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceConfig userDetailServiceConfig;
    @Autowired
    UserRepositoty userRepositoty;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceConfig)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000);
        http
                .authorizeRequests()
                .antMatchers("/","/home","/add", "/api/login").permitAll()
//                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/user/**").hasAnyAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureHandler((request, response, exception) -> {
                    response.sendRedirect("/login");
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        CustomUser userEntity = (CustomUser)  authentication.getPrincipal();
                        userEntity.getUserEntity().getRoless()
                                .stream()
                                .filter(u -> {
                                    if(u.getName().equals("ADMIN") || u.getName().equals("ADMIN_READ")){
                                        try {
                                            UserDto uz = userRepositoty.findByUserPass(userEntity.getUsername(), userEntity.getPassword());
                                            HttpSession session = request.getSession();
                                            session.setAttribute("user", uz);
                                            response.sendRedirect("/admin/");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if(u.getName().equals("USER")){
                                        try {
                                            UserDto uz = userRepositoty.findByUserPass(userEntity.getUsername(), userEntity.getPassword());
                                            HttpSession session = request.getSession();
                                            session.setAttribute("user", uz);
                                            response.sendRedirect("/user/");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    return true;
                                }).findAny().orElse(null);
                    }
                })
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
    }


}
