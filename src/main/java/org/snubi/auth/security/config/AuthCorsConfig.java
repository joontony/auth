/**************************************************************************************** 
 * File Name    : BioEmrCorsConfig.java
 * Function     : 
 * Author       : mh.choi
 * Tester       : 
 * Page         : 
 * Target       : 
 * Description  : Auth
 * Modification Log
 * =============================================================== 
 * Ver  Date        Author     Modification
 * ===============================================================
   1.0  2018.04.11  mh.choi    Create
      	2018.04.11  mh.choi    CorsRegistry 등록 설정 추가   	
****************************************************************************************/
package org.snubi.auth.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AuthCorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
    	
        return new WebMvcConfigurerAdapter() {        	
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH")
						.allowedOrigins("*")
						.allowedHeaders("*");
            }
        };
    }
}