/****************************************************************************************
 * File Name    : AuthMemberDevice.java
 * Function     :
 * Author       : 최명호
 * Tester       :
 * Page         :
 * Target       :
 * Description  :
 * Modification Log
 * ======================================================================================
 * Ver  Date        Author     	Modification
 * ======================================================================================
   1.0  2018.04.06  최명호		Create
   1.1  2020.09.11  최명호		system_type_code / system_type_version 추가
****************************************************************************************/
package org.snubi.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.snubi.lib.code.CodeUtil;
import org.snubi.lib.date.DateUtil;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@ToString
@Table(name = "tb_auth_member_device")
@Slf4j
public class AuthMemberDevice extends CommonTrack implements CommonTrackInterface {

	@Id
	@GeneratedValue
	@Column(nullable = false, name = "seq",columnDefinition="int(11)")
	private long seq;

	@Column(nullable = false, name = "id")
	private String id;

	@Column(nullable = false, name = "system_code")
	private String systemCode;

	@Column(nullable = true, name = "mobile")
	private String mobile;

	@Column(nullable = false, name = "device_id")
	private String deviceId;

	@Column(nullable = true, name = "os_code")
	private String osCode;

	@Column(nullable = true, name = "system_type_code")
	private String systemTypeCode;

	@Column(nullable = true, name = "system_type_version")
	private String systemTypeVersion;

	public AuthMemberDevice() {

	}
	@Builder(builderMethodName = "builder")
	public AuthMemberDevice(String id, String systemCode, String mobile, String deviceId,String osCode,String systemTypeCode,String systemTypeVersion) {
		this.id = id;
		this.mobile = mobile;
		this.deviceId = deviceId;
		this.systemCode = systemCode;
		this.osCode = osCode;
		this.systemTypeCode = systemTypeCode;
		this.systemTypeVersion = systemTypeVersion;
	}
	@PrePersist
    public void prePersist() {
		try {
			this.setCreator(this.id);
			this.setUpdater(this.id);
			this.setUpdated(DateUtil.getThisSQLDate());
			this.setCreated(DateUtil.getThisSQLDate());
			this.setValid(CodeUtil.get("bioemr-activate-active").getStrCode());
		} catch (Exception e) {
			log.debug("## AuthMemberDevice ## prePersist 오류 ");
		}
    }
	@PreUpdate
    public void preUpdate() {
		try {
			this.setUpdater(this.id);
			this.setUpdated(DateUtil.getThisSQLDate());
			this.setValid(CodeUtil.get("bioemr-activate-active").getStrCode());
		} catch (Exception e) {
			log.debug("## AuthMemberDevice ## preUpdate 오류 ");
		}
    }
}
