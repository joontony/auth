/****************************************************************************************
 * File Name    : AuthMemberRole.java
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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@ToString
@Table(name = "tb_auth_member_role")
public class AuthMemberRole extends CommonTrack implements CommonTrackInterface {

	@Id
	@GeneratedValue
	@Column(nullable = false, name = "seq",columnDefinition="int(11)")
	private long seq;

	@Column(nullable = false, name = "id")
	private String id;

	@Column(nullable = false, name = "system_code")
	private String systemCode;

	@Column(nullable = false, name = "role_code")
	private String roleCode;

	public AuthMemberRole(String id, String systemCode, String roleCode) {
		this.id = id;
		this.systemCode = systemCode;
		this.roleCode = roleCode;
	}
	public AuthMemberRole() {

	}
}

