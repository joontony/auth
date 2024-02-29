/**********************************************************************************************************************
 * File Name    : AuthMember.java
 * Function     :
 * Author       : 최명호
 * Tester       :
 * Page         :
 * Target       :
 * Description  :
 * Modification Log
 * ====================================================================================================================
 * Ver  	Date        Author     Modification
 * ====================================================================================================================
   1.0  	2018.04.06  최명호    	Create
   1.4.004	2021.11.15	최명호 		cryptographicAlgorithm 에 따라 복호화 여부를 판단한다,
   									AES256 이면 복호화후에 비교, SAH256 이라면, Digested 된 값과 비교한다
***********************************************************************************************************************/
package org.snubi.auth.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.snubi.auth.AuthConst;
import org.snubi.lib.crypto.AES256Util;
import org.snubi.lib.crypto.SHA256Util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@ToString
//@Table(name = "tb_auth_member",schema="auth")
@Table(name = "tb_auth_member")
@Slf4j
public class AuthMember extends CommonTrack {

	@Id
	@Column(nullable = false, name = "id")
	private String id;

	@Column(nullable = false, name = "organization_code")
	private String organizationCode;

	@Column(nullable = false, name = "department_code")
	private String departmentCode;

	@Column(nullable = true, name = "title_code")
	private String titleCode;

	@Column(nullable = true, name = "gender_code")
	private String genderCode;

	@Column(nullable = false, name = "membership_code")
	private String membershipCode;

	@Column(nullable = false, name = "password")
	private String password;

	@Column(nullable = false, name = "cryptographic_algorithm")
	private String cryptographicAlgorithm;


	@Column(nullable = false, name = "name")
	private String name;

	@Column(nullable = false, name = "email")
	private String email;

	@Column(nullable = true, name = "birth")
	@CreationTimestamp
	private Date birth;

	@Column(nullable = true, name = "phone")
	private String phone;

	@Column(nullable = true, name = "mobile")
	private String mobile;

	@Transient
	long count;

	public AuthMember(String id, String organizationCode, String departmentCode) {
		this.id = id;
		this.organizationCode = organizationCode;
		this.departmentCode = departmentCode;
	}
	public AuthMember() {}
	public void encode() {
		try {
			AES256Util clsAES256Util = new AES256Util(AuthConst.strAES256Key);
			this.name 		= clsAES256Util.aesEncode(this.name);
			this.password	= this.isDigested() == false ? clsAES256Util.aesEncode(this.password) : SHA256Util.encrypt(this.password);
		} catch(Exception Ex) {
			log.debug("## AuthMember ## encode 오류 ");
		}
	}
	public void decode() throws Exception {
		try {
			AES256Util clsAES256Util = new AES256Util(AuthConst.strAES256Key);
			this.name 		= clsAES256Util.aesDecode(this.name);
			this.password 	= this.isDigested() == false ? clsAES256Util.aesEncode(this.password) : this.password;
		} catch(Exception Ex) {
			log.debug("## AuthMember ## decode 오류 ");
		}
	}
//	DESC >> 최명호 : 암호화가 AES 로 되어 있다면, false, SHA-256 으로 되어 있다면 true
//	TODO >> 최명호 : 코드에 반영해야 한다.
	public boolean isDigested() {
		return this.cryptographicAlgorithm.toUpperCase().charAt(0) == AuthConst._AES_256_.charAt(0) ? false : true;
	}
}
