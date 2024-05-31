package com.example.fruitMall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {		//BCryptPasswordEncoder의 객체를 생성하기 위한 메서드
		return new BCryptPasswordEncoder();
		}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/members/**").hasAnyRole("ADMIN","MEMBER")	//2개이상일때 hasAnyRole
				.requestMatchers("/admin/**").hasRole("ADMIN")					//1개일때 hasRole
//				.requestMatchers("/","/regForm","/regist").permitAll()	//해당 리퀘스트에서는 인증을 안해도 됨(전부허용)
				.anyRequest().permitAll()									//나머지 리퀘스트는 인증을 거치도록 함
		);
		
		http.formLogin((auth) ->auth											
				.loginPage("/login")		//로그인폼 지정-쓰지 않으면 Spring Security가 제공하는 로그인 폼 사용
				.loginProcessingUrl("/loginProc")		//로그인 폼 지정 후 폼 파라미터 보내는 경로 지정 - 컨트롤러에 직접 만들지 않는다.(Spring Security가 알아서 처리함)
				.defaultSuccessUrl("/")		//로그인에 성공했을때 이동할 리퀘스트
				.permitAll()
				);
		
//		http.logout((auth) -> auth 
//                .logoutUrl("/logout")                           // 로그아웃 URL 지정
//                .logoutSuccessUrl("/")          // 로그아웃 성공 시 이동할 URL
//                .invalidateHttpSession(true)                    // 세션 무효화
//                .deleteCookies("JSESSIONID")                    // 쿠키 삭제
//                .permitAll()
//        );
		
		http.csrf(AbstractHttpConfigurer::disable);		//csrf 기능 끄기
		return http.build();
	}
}
