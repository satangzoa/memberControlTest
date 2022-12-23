package com.pengsoo.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.FormLoginBeanDefinitionParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pengsoo.home.service.MemberService;


@Configuration//설정파일임을 선언 
@EnableWebSecurity//보안관련 설정파일
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	MemberService memberService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
        .authorizeRequests()
            .antMatchers("/","/**").access("permitAll")
            .antMatchers("/h2-console/**").permitAll() // 추가
        .and()
            .csrf() // 추가
            .ignoringAntMatchers("/h2-console/**").disable() // 추가
            .httpBasic();
		
		http.formLogin()
			.loginPage("/login")//로그인 페이지 url
			.defaultSuccessUrl("/inginOk")//로그인 성공시 이동할 url
			.usernameParameter("mid") //로그인 시 사용할 파라미터 이름
			.passwordParameter("mpw")
			.failureUrl("/loginFail") //로그인 실패시 이동할 url
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//로그 아웃 url
			.logoutUrl("/index");//로그아웃 성공시 이동할 url
	}

	public void configure(WebSecurity web)throws Exception{
        web.ignoring().antMatchers("/h2-console/**");
    }
	
	
	@Bean//암호를 암호화 시킨다
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}
	
	
	
}













