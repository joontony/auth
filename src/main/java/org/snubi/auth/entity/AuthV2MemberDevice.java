/****************************************************************************************
 * File Name    : AuthV2MemberDevice.java
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
   1.0  2020.09.09  최명호		Create
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
@Table(name = "tb_auth_member_device",schema="auth_v2")
public class AuthV2MemberDevice extends CommonTrack implements CommonTrackInterface {

	@Id
	@GeneratedValue
	@Column(nullable = false, name = "seq_auth_member_device")
	private long seqAuthMemberDevice;

//	DESC >> 사용자 기관정보
	@JsonIgnore
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn(name = "seq_auth_member_organization", nullable = false )
	AuthV2MemberOrganization authV2MemberOrganization;

	@Column(nullable = false, name = "system_code")
	private String systemCode;

	@Column(nullable = false, name = "system_type_code")
	private String systemTypeCode;

	@Column(nullable = false, name = "system_type_version")
	private String systemTypeVersion;

	@Column(nullable = true, name = "mobile")
	private String mobile;

	@Column(nullable = false, name = "device_id")
	private String deviceId;

	@Column(nullable = true, name = "os_code")
	private String osCode;

	@Column(nullable = true, name = "valid")
	private String valid;



	public AuthV2MemberDevice() {

	}
}
