/**************************************************************************************** 
 * File Name    : CustomLogoutSuccessHandler.java
 * Function     : 
 * Author       : mh.choi
 * Tester       : 
 * Page         : 
 * Target       : 
 * Description  : 
 * Modification Log
 * =============================================================== 
 * Ver  Date        Author     	Modification
 * ===============================================================
   1.0  2018.04.27	mh.choi		create
****************************************************************************************/
package org.snubi.auth.security.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.snubi.auth.packet.PacketAuthGetToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
 
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{
		
	@Value("${packet.response.code}") 		
	String packetResponseCode;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest requestOrg, HttpServletResponse responseOrg, Authentication authentication) throws IOException, ServletException {
		@SuppressWarnings("rawtypes")
		PacketAuthGetToken packetAuth = new PacketAuthGetToken();
    	packetAuth.set("success","logoff");
    	responseOrg.setStatus(Integer.parseInt(packetResponseCode));
    	responseOrg.getWriter().write(packetAuth.respone());    
	}
}