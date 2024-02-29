/****************************************************************************************
 * File Name    : AuthV2MemberRole.java
 * Function     :
 * Author       : mh.choi
 * Tester       :
 * Page         :
 * Target       :
 * Description  :
 * Modification Log
 * ======================================================================================
 * Ver  Date        Author     Modification
======================================================================================
   1.0  2020.09.09  최명호    Create
****************************************************************************************/
package org.snubi.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@ToString
@Table(name = "tb_auth_member_role",schema="auth_v2")
public class AuthV2MemberRole extends CommonTrack implements CommonTrackInterface {

	@Id
	@GeneratedValue
	@Column(nullable = false, name = "seq_auth_member_role")
	private long seqAuthMemberRole;

//	DESC >> 사용자 기관정보
	@JsonIgnore
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn(name = "seq_auth_member_organization", nullable = false )
	AuthV2MemberOrganization authV2MemberOrganization;

	@Column(nullable = false, name = "system_code")
	private String systemCode;

	@Column(nullable = false, name = "system_type_code")
	private String systemTypeCode;

	@Column(nullable = false, name = "role_code")
	private String roleCode;

	public AuthV2MemberRole() {

	}
}

