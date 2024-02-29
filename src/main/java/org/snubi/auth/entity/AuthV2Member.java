/****************************************************************************************
 * File Name    : AuthV2Member.java
 * Function     :
 * Author       : mh.choi
 * Tester       :
 * Page         :
 * Target       :
 * Description  :
 * Modification Log
 * ===============================================================
 * Ver  Date        Author  Modification
 * ===============================================================
   1.0  2020.09.09	최명호 	파일생성
****************************************************************************************/
package org.snubi.auth.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.snubi.auth.AuthConst;
import org.snubi.lib.crypto.AES256Util;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@ToString
@Table(name = "tb_auth_member",schema="auth_v2")
@Slf4j
public class AuthV2Member extends CommonTrack {

	@Id
	@Column(nullable = false, name = "id")
	private String id;

	@Column(nullable = true, name = "gender_code")
	private String genderCode;

	@Column(nullable = false, name = "membership_code")
	private String membershipCode;

	@Column(nullable = false, name = "password")
	private String password;

	@Column(nullable = false, name = "name")
	private String name;

	@Column(nullable = false, name = "email")
	private String email;

	@Column(nullable = true, name = "birth")
	private Date birth;

	@Column(nullable = true, name = "phone")
	private String phone;

	@Column(nullable = true, name = "valid")
	private String valid;

//	DESC >> 사용자 기관정보
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "authV2Member")
	private List<AuthV2MemberOrganization> listAuthV2MemberOrganization;

	public AuthV2Member(String id) {
		this.id = id;
	}
	public AuthV2Member() {}
	public void encode() {
		try {
			AES256Util clsAES256Util = new AES256Util(AuthConst.strAES256Key);
			this.name 		= clsAES256Util.aesEncode(this.name);
			this.password 	= clsAES256Util.aesEncode(this.password);
		} catch(Exception Ex) {
			log.debug("## AuthV2Member ## encode 오류 ");
		}
	}
	public void decode() throws Exception {
		try {
			AES256Util clsAES256Util = new AES256Util(AuthConst.strAES256Key);
			this.name 		= clsAES256Util.aesDecode(this.name);
			this.password 	= clsAES256Util.aesDecode(this.password);
		} catch(Exception Ex) {
			log.debug("## AuthV2Member ## decode 오류 ");
		}
	}
}
