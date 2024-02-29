/**************************************************************************************** 
 * File Name    : AuthRoleMapElements.java
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
package org.snubi.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@ToString
@Table(name = "tb_auth_role_map_elements")
public class AuthRoleMapElements extends CommonTrack {
	
	@Id
	@GeneratedValue
	@Column(nullable = false, name = "seq_auth_role_map_elements",columnDefinition="int(11)")
	private long seqAuthRoleMapElements;
	
	@NotNull
	@GeneratedValue
	@Column(nullable = false, name = "seq_auth_role_map",columnDefinition="int(11)")
	private long seqAuthRoleMap;
		
	@NotNull
	@Column(nullable = false, name = "system_code")
	@Size(min = 1, max = 20)
	private String systemCode;
	
	@NotNull
	@Column(nullable = false, name = "role_code")
	@Size(min = 1, max = 20)
	private String roleCode;	
	
}
