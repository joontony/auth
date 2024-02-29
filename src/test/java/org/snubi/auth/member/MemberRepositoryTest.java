/****************************************************************************************
 * File Name    : MemberRepositoryTest.java
 * Function     :
 * Author       : mh.choi
 * Tester       :
 * Page         :
 * Target       :
 * Description  : Lombok Getter/Setter not working
 * 				  https://stackoverflow.com/questions/11803948/lombok-is-not-generating-getter-and-setter
 * Modification Log
 * ===============================================================
 * Ver  Date        Author     Modification
 * ===============================================================
   1.0  2018.04.06  mh.choi    Create
****************************************************************************************/
package org.snubi.auth.member;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.snubi.auth.entity.AuthMember;
import org.snubi.auth.entity.AuthMemberDevice;
import org.snubi.auth.entity.AuthMemberRole;
import org.snubi.auth.entity.AuthMemberSet;
import org.snubi.auth.member.repository.AuthMemberRepository;
import org.snubi.auth.member.repository.AuthMemberSetRepository;
import org.snubi.lib.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class MemberRepositoryTest {

	@Autowired
	AuthMemberRepository clsAuthMemberRepository;

	@Autowired
	AuthMemberSetRepository clsAuthMemberSetRepository;


	@Test
	public void insertAuthMemberTest() {

		AuthMember clsAuthMember = new AuthMember();
		clsAuthMember.setBirth(DateUtil.getThisSQLDate());
		clsAuthMember.setDepartmentCode("department001");
		clsAuthMember.setEmail("email001");
		clsAuthMember.setGenderCode("genderCode001");
		clsAuthMember.setId("idid001");
		clsAuthMember.setMembershipCode("membershipCode001");
		clsAuthMember.setMobile("01031764592");
		clsAuthMember.setName("이름001");
		clsAuthMember.setOrganizationCode("organizationCode001");
		clsAuthMember.setPassword("password");
		clsAuthMember.setPhone("phone001");
		clsAuthMember.setTitleCode("titleCode001");
//		clsAuthMember.setTrackDefault();
		AuthMember clsTarget = clsAuthMemberRepository.save(clsAuthMember);
		assertTrue(clsTarget.getId().equals(clsAuthMember.getId()));
	}

	@Test
	public void insertAuthMemberSetTest() {

		String strId = "idid002";
		String strSystemCode = "system001";
		String strMobile = "01031764592";
		String strDeviceId = "device001";
		String strRole = "role001";

		AuthMemberSet clsAuthMemberSet = new AuthMemberSet();
		clsAuthMemberSet.setBirth(DateUtil.getThisSQLDate());
		clsAuthMemberSet.setId(strId);
		clsAuthMemberSet.setDepartmentCode("department001");
		clsAuthMemberSet.setEmail("email001");
		clsAuthMemberSet.setGenderCode("genderCode001");
		clsAuthMemberSet.setMembershipCode("membershipCode001");
		clsAuthMemberSet.setMobile(strMobile);
		clsAuthMemberSet.setName("이름001");
		clsAuthMemberSet.setOrganizationCode("organizationCode001");
		clsAuthMemberSet.setPassword("password");
		clsAuthMemberSet.setPhone("phone001");
		clsAuthMemberSet.setTitleCode("titleCode001");

		List<AuthMemberDevice> listAuthMemberDevice = new ArrayList<AuthMemberDevice>();
		List<AuthMemberRole> listAuthMemberRole = new ArrayList<AuthMemberRole>();

		listAuthMemberDevice.add(new AuthMemberDevice(strId, strSystemCode, strMobile, strDeviceId,"android","beans","1.0"));
		listAuthMemberRole.add(new AuthMemberRole(strId, strSystemCode, strRole));
		clsAuthMemberSet.setListAuthMemberDevice(listAuthMemberDevice);
		clsAuthMemberSet.setListAuthMemberRole(listAuthMemberRole);
//		clsAuthMemberSet.setTrackDefault();

		AuthMemberSet clsTarget = clsAuthMemberSetRepository.save(clsAuthMemberSet);
		assertTrue(clsTarget.getId().equals(clsAuthMemberSet.getId()));
	}

}