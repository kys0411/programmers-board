package programmers.noticeBoard.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import programmers.noticeBoard.config.auth.UserDetailsServiceImpl;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests() // 리퀘스트에 대한 사용권한 체크
                .antMatchers("/api/**").permitAll()
                .antMatchers("/", "/join", "/login", "/search/**", "/login/error").permitAll()
                .antMatchers(HttpMethod.GET, "/post/**").permitAll()
                .antMatchers(HttpMethod.GET, "/member/**").permitAll()
                .antMatchers("**exception**").permitAll()

                .anyRequest().authenticated();  // 나머지 요청은 인증된 USER만 접근 가능

        httpSecurity.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .failureUrl("/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
        ;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}