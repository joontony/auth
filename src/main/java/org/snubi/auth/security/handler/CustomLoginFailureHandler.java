/**************************************************************************************** 
 * File Name    : CustomLoginFailureHandler.java
 * Function     : 
 * Author       : mh.choi
 * Tester       : 
 * Page         : 
 * Target       : 
 * Description  : 
 * Modification Log
 * =============================================================== 
 * Ver  Date        Author     Modification
 * ===============================================================
   1.0  2018.04.06  mh.choi    Create
****************************************************************************************/
package org.snubi.auth.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.snubi.auth.packet.PacketAuthGetToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Value("${packet.response.code}") 		
	String packetResponseCode;
	/**  
	 * onAuthenticationFailure
	 * @author mh.choi
	 * @version 1.0
	 * @parameter 
	 * @return 
	 */
    @Override
    public void onAuthenticationFailure(HttpServletRequest requestOrg, HttpServletResponse responseOrg, AuthenticationException exception) 
    		throws IOException, ServletException {
    	@SuppressWarnings("rawtypes")
		PacketAuthGetToken packetAuth = new PacketAuthGetToken();
    	packetAuth.set(null,"login-fail");
    	responseOrg.setStatus(Integer.parseInt(packetResponseCode));
    	responseOrg.getWriter().write(packetAuth.respone());    	
    }
}