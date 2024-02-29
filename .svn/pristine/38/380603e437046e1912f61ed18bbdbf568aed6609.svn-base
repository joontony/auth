/**************************************************************************************************
 * File Name    : AuthSecurityMember.java
 * Function     :
 * Author       : 최명호
 * Tester       :
 * Page         :
 * Target       :
 * Description  :
 * Modification Log
 * ================================================================================================
 * Ver  Date        Author  Modification
 * ================================================================================================
   1.0  2018.04.06  최명호  Create
   1.1  2018.09.18  최명호  JWT내 exp를 사용하지 않는다.왜냐하면,결과를클라이언트로 전달할수없다.
   1.2	2019.06.28	최명호	JWT 에 권한및 DeviceID 를 추가한다
   1.3	2020.09.10	최명호 	회원정보처리를 위한 API-VERSION 을 추가한다.
***************************************************************************************************/
package org.snubi.auth.security;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.snubi.auth.AuthConst;
import org.snubi.auth.entity.AuthMemberDevice;
import org.snubi.auth.entity.AuthMemberRole;
import org.snubi.auth.entity.AuthMemberSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthSecurityMember extends User {

	private static final String ROLE_PREFIX = "ROLE_";
	private static final long serialVersionUID = 1L;
	private Map<String,Object> mapClaims = new HashMap<String,Object>();
	public  AuthMemberSet clsAuthMember;
//	DESC >> 권한 : default
	static List<String> listString = new ArrayList<String>();
	static {
		listString.add("DEFAULT");
	}
	private long apiVersion = 0;

	public AuthSecurityMember(AuthMemberSet clsAuthMember) {
		super(clsAuthMember.getId(), clsAuthMember.getPassword(),makeGrantedAuthority(listString));
		this.clsAuthMember = clsAuthMember;
	}
	private static List<GrantedAuthority> makeGrantedAuthority(List<String> listString){
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(ROLE_PREFIX + listString.get(0)));
		return list;
	}
	public Map<String,Object> getClaims() {
		clsAuthMember.decode();
		SimpleDateFormat clsSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        Date dateExpired = new Date(System.currentTimeMillis() + AuthConst.longExpireMilliSecond);
		mapClaims.put(AuthConst.strResponseAuthClaimSub			,clsAuthMember.getId());
		mapClaims.put(AuthConst.strResponseAuthClaimName		,clsAuthMember.getName());
		mapClaims.put(AuthConst.strResponseAuthClaimId			,clsAuthMember.getId());
		mapClaims.put(AuthConst.strResponseAuthClaimOrganization,clsAuthMember.getOrganizationCode());
		mapClaims.put(AuthConst.strResponseAuthClaimDepartment	,clsAuthMember.getDepartmentCode());
		mapClaims.put(AuthConst.strResponseAuthClaimTitle		,clsAuthMember.getTitleCode());
		mapClaims.put(AuthConst.strResponseAuthClaimIssue		,clsSimpleDateFormat.format(dateExpired));

//		DESC >>  Device-ID 를 JWT 토큰에 추가함.
		List<AuthMemberDevice> listAuthMemberDevice = clsAuthMember.getListAuthMemberDevice();
		List<String> listDeviceID = new ArrayList<String>();
		for(AuthMemberDevice clsAuthMemberDevice : listAuthMemberDevice) {
			listDeviceID.add(clsAuthMemberDevice.getDeviceId());
		}
		mapClaims.put(AuthConst.strResponseAuthClaimDeviceID,listDeviceID);
//		DESC >> 시스템별 권한을 설정
		for(AuthMemberRole clstAuthMemberRole : clsAuthMember.getListAuthMemberRole()) {
			mapClaims.put(clstAuthMemberRole.getSystemCode(),clstAuthMemberRole.getRoleCode());
		}
		return mapClaims;
	}
}
