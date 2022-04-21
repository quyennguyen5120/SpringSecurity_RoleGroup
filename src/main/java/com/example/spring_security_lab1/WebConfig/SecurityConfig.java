package com.example.spring_security_lab1.WebConfig;


import com.example.spring_security_lab1.Repository.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceConfig userDetailServiceConfig;

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
        http.authorizeRequests().antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .antMatchers("/user/**").hasAnyAuthority("USER");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests()
                .antMatchers("/","/home","/add").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        CustomUser userEntity = (CustomUser)  authentication.getPrincipal();
                        if(userEntity.getUserEntity().getRole().equals("ADMIN") || equals("MENTOR")){
                            response.sendRedirect("/admin/");
                        }
                        else {
                            response.sendRedirect("/user/");
                        }
                    }
                })
                .loginPage("/login")
                .failureHandler((request, response, exception) -> {
                    String name = request.getParameter("username");
                    System.out.println();
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
    }

}
