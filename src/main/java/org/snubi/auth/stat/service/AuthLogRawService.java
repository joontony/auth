/**************************************************************************************** 
 * File Name    : AuthLogRawService.java
 * Function     : 
 * Author       : mh.choi
 * Tester       : 
 * Page         : 
 * Target       : 
 * Description  : 
 * Modification Log
 * ====================================================================================== 
 * Ver  Date        Author     	Modification
 * ======================================================================================
   1.0  2019.04.30	최명호		파일생성   
****************************************************************************************/
package org.snubi.auth.stat.service;

import java.util.Map;

import org.snubi.auth.entity.AuthLogRaw;
import org.springframework.stereotype.Service;

@Service
public interface AuthLogRawService {	
	public AuthLogRaw save(AuthLogRaw clsParameter) throws Exception;
	public boolean state(Map<String,Object> malParameter)  throws Exception;
}
 