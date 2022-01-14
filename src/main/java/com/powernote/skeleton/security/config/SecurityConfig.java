package com.powernote.skeleton.security.config;

import com.powernote.skeleton.security.UserRoleE;
import com.powernote.skeleton.security.handler.CustomAccessDeniedHandler;
import com.powernote.skeleton.security.handler.CustomAuthenticationEntryPoint;
import com.powernote.skeleton.security.handler.CustomLoginFailureHandler;
import com.powernote.skeleton.security.handler.CustomLoginSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
// @EnableGlobalMethodSecurity(    // Method 단위 시큐리티 처리.
//        prePostEnabled = true,  // Spring Security의 @PreAuthorize, @PreFilter /@PostAuthorize, @PostFilter어노테이션 활성화 여부
//        securedEnabled = true,  // @Secured어노테이션 활성화 여부
//        jsr250Enabled = true)   // @RoleAllowed 어노테이션 사용 활성화 여부
@EnableWebSecurity  // EnableWebSecurity 를 입력하는 순간 기존 Spring의 설정을 무시하고 내가 설정하겠다고 지정하는 것임.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 추가적인 provider가 구현이 필요한경우 포함 가능함.
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("aaaa@gmail.com")
//                .password(passwordEncoder().encode("aaaa1234")).roles("USER")
//                .and()
//                .withUser("bhkim@zinnaworks.com")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER").roles("ADMIN");
//    }

    @Override // ignore check swagger resource 해당 url은 시큐리티 처리 안함.
    public void configure(WebSecurity web)  throws Exception  {
        //        super.configure(web);
//        web.ignoring().antMatchers("/swagger-ui/**","/swagger-resources/**","/v2/api-docs/**", "/sample/**");
        web.ignoring().antMatchers("/", "/index","/vendor/**", "/css/**", "/images/**", "/js/**", "/error/**" );
        web.ignoring().antMatchers(  "/user/regist", "/user/user_post_regist" );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);

        http
            .csrf().disable()
            .authorizeRequests()  // 요청을 어떻게 보안을 걸것이냐에 대한 설정.
                .antMatchers("/", "/main","/regist").permitAll()
                .antMatchers("/hello").hasRole( UserRoleE.ROLE_USER.getRole() )
                .antMatchers("/board/**").hasRole( UserRoleE.ROLE_USER.getRole() )
//                .anyRequest().authenticated() // 이외에는 인증이 필요하다.
                .and()
            .formLogin()          // 로그인 페이지 관련. 이항목이 없다면 403 페이지 오류가 발생.
                .loginPage("/user/login").permitAll()  // 사용자 페이지 구성을 위해서는 해당 항목을 설정.
                .loginProcessingUrl("/j_spring_security_check").permitAll()
                .usernameParameter("userid")
                .passwordParameter("password")
                .successHandler( new CustomLoginSuccessHandler("/main") )
                .failureHandler( new CustomLoginFailureHandler("/user/login") )
                .and()
            .logout()             // 로그아웃시 어떻게 처리 할것이냐.
                .logoutSuccessUrl("/login").permitAll()   // logout 이 되고 나서 가는 페이지 설정.
                .invalidateHttpSession(true)  // session 정보를 지우고 무효화.
//                .and()
//            .exceptionHandling()  // Exception Handle 의 경우  필요한경우에만 설정
//                // 로그인은 됐으나 Role 접근이 안되는 경우. '권한 없음 페이지. 403 Status 처리.'
//                .accessDeniedHandler(new CustomAccessDeniedHandler())
//                // 비로그인 상태. 인증 하지 않은 상황에서 호출됨으로 401.html 페이지 호출 이후  로그인 할수 있는 페이지 이동 가이드가 필요함.
//                // authenticationEntryPoint를 설정시 로그인 페이지를 호출하지 않을 수 있다.
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
            ;
    }
}
