/****************************************************************************************
 * File Name    : AuthSecurityConfig.java
 * Function     :
 * Author       : mh.choi
 * Tester       :
 * Page         :
 * Target       :
 * Description  : 로그인 및 회원가입과 관련된 security Config 설정
 * 				  참고자료 https://xmfpes.github.io/spring/spring-security/
 * Modification Log
 * ===============================================================
 * Ver  Date        Author     Modification
 * ===============================================================
   1.0  2018.04.06  mh.choi    Create
   		2018.04.11  mh.choi    CORS Filter 등록 -> 삭제
****************************************************************************************/
package org.snubi.auth.security.config;

import org.snubi.auth.security.filter.AuthJWTAuthenticationFilter;
import org.snubi.auth.security.filter.AuthJWTAuthorizationFilter;
import org.snubi.auth.security.handler.CustomLoginFailureHandler;
import org.snubi.auth.security.handler.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@EnableWebSecurity
public class AuthSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	@Autowired
	CustomLogoutSuccessHandler customLogoutSuccessHandler;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/script/**", "/image/**", "/fonts/**", "/lib/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors();
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/join").permitAll()
			.antMatchers("/find/**").permitAll()
			.antMatchers("/search/**").authenticated()
			.antMatchers("/member/**").authenticated()
			.antMatchers("/admin/**").authenticated()
			.and()
			.addFilter(new AuthJWTAuthenticationFilter(authenticationManager(), getApplicationContext()))
			.addFilter(new AuthJWTAuthorizationFilter(authenticationManager()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	@Bean
	public AuthenticationFailureHandler failureHandler() {
	    return new CustomLoginFailureHandler();
	}
}
