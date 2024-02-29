/****************************************************************************************
 * File Name    : AuthV2MemberOrganization.java
 * Function     :
 * Author       : mh.choi
 * Tester       :
 * Page         :
 * Target       :
 * Description  : @ElementCollection(fetch = FetchType.EAGER) 애노테이션을 2번 이상 사용한 경우에
 * 				: org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags 이 발생될 수 있다
 * Modification Log
 * ======================================================================================
 * Ver  Date        Author     Modification
 * ======================================================================================
   1.0  2020.09.09  최명호    Create
****************************************************************************************/
package org.snubi.auth.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@ToString
@Table(name = "tb_auth_member_organization",schema="auth_v2")
public class AuthV2MemberOrganization extends CommonTrack {

	@Id
	@Column(nullable = false, name = "seq_auth_member_organization")
	private String seqAuthMemberOrganization;

//	DESC >> 사용자정보
	@JsonIgnore
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn(name = "id", nullable = false )
	AuthV2Member authV2Member;

	@Column(nullable = false, name = "organization_code")
	private String organizationCode;

	@Column(nullable = false, name = "department_code")
	private String departmentCode;

	@Column(nullable = true, name = "title_code")
	private String titleCode;

	@Column(nullable = true, name = "valid")
	private String valid;

	@NotFound(action = NotFoundAction.IGNORE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "authV2MemberOrganization")
	private List<AuthV2MemberDevice> listAuthV2MemberDevice;

	@NotFound(action = NotFoundAction.IGNORE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "authV2MemberOrganization")
	private List<AuthV2MemberRole> listAuthV2MemberRole;

	public AuthV2MemberOrganization() {}
}
