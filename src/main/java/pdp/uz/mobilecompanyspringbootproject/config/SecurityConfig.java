package pdp.uz.mobilecompanyspringbootproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pdp.uz.mobilecompanyspringbootproject.service.UserService;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new  BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("director").password(passwordEncoder().encode("director")).roles("DIRECTOR")
                .and()
                .withUser("manager").password(passwordEncoder().encode("manager")).roles("MANAGER")
                .and()
                .withUser("employee").password(passwordEncoder().encode("employee")).roles("EMPLOYEE")
                .and()
                .withUser("customer").password(passwordEncoder().encode("customer")).roles("CUSTOMER")
                .and()
                .withUser("operator").password(passwordEncoder().encode("operator")).roles("OPERATOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/register","/api/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


}
