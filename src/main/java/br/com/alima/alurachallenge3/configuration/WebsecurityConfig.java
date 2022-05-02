package br.com.alima.alurachallenge3.configuration;

import br.com.alima.alurachallenge3.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebsecurityConfig extends WebSecurityConfigurerAdapter {

    AuthService authService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                );

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
