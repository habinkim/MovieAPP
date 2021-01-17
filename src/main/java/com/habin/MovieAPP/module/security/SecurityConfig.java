package com.habin.MovieAPP.module.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http
		.cors()
			.and()
    	.csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
    	.httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
        .formLogin()     	// form 기반의 로그인에 대해 비활성화 한다.
            .disable()
    	// 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다.
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//     		.and()
//     	.authorizeRequests() // 권한 체크
// //    	.antMatchers("/admin/checkid", "/admin/signup", "/admin/login", "/admin/refresh", "/admin/logout").permitAll()
//         // 토큰을 활용하는 경우 모든 요청에 대해 접근이 가능하도록 함
// //        	.anyRequest().authenticated()
// 		.anyRequest().permitAll()
//         	.and()
//         .exceptionHandling().authenticationEntryPoint(authEntryPoint)
//     		.and()
//     	.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
// 	    			UsernamePasswordAuthenticationFilter.class);

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 사용자 세부 서비스를 설정하기 위한 오버라이딩이다.
//    	auth.userDetailsService(userService);
//
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Cookie", "Accept", "Accept-Encoding", "TOKEN", "Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

