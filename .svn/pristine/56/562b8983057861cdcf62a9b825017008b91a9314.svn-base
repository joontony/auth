/**************************************************************************************** 
 * File Name    : PacketAuthPut.java
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
   1.0  2018.05.17  mh.choi    Create
****************************************************************************************/
package org.snubi.auth.packet;

import org.snubi.auth.member.service.AuthMemberService;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("rawtypes")
@Getter
@Setter
@Component
public class PacketAuthPut<T> extends PacketAuth<T> {
	
	@Override
	public String respone() {
		return null;
	}
	@Override
	boolean post(T clsParameter) throws Exception {
		return false;
	}
	@Override
	boolean get(T clsParameter) throws Exception {
		return false;
	}
	@Override
	boolean put(T clsParameter) throws Exception {
		return false;
	}
	@Override
	boolean delete(T clsParameter) throws Exception {
		return false;
	}
	
	AuthMemberService clsMemberServiceImplement;	 
	
	public void setService(AuthMemberService clsMemberServiceImplement) {
		this.clsMemberServiceImplement = clsMemberServiceImplement;
	}
}
