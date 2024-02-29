/**************************************************************************************** 
 * File Name    : EnumMemberRole.java
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
   1.0  2018.04.09  mh.choi    Create
****************************************************************************************/
package org.snubi.auth.packet.enums;

public enum EnumMemberRole {
	
	USER  	("USER"		),
	ADMIN  	("ADMIN"	),
	DIRECTOR("DIRECTOR" );
	
	private final String text;    
	EnumMemberRole(final String text) {
	      this.text = text;
	}    
	@Override
	public String toString() {
		return text;
	}
}

