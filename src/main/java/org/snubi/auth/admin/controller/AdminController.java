/****************************************************************************************
 * File Name    : AdminController.java
 * Function     :
 * Author       : 최명호
 * Tester       :
 * Page         :
 * Target       :
 * Description  : member controller
 * Modification Log
 * ======================================================================================
 * Ver  Date        Author     Modification
 * ======================================================================================
   1.0  2019.12.12  최명호    	Create
   1.1	2020.02.10	최명호 		getUserList 기관코드/타이틀코드 필터추가
****************************************************************************************/
package org.snubi.auth.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.snubi.auth.AuthConst;
import org.snubi.auth.controller.WithAuthController;
import org.snubi.auth.entity.AuthMember;
import org.snubi.auth.entity.AuthMemberRole;
import org.snubi.auth.entity.AuthMemberSet;
import org.snubi.auth.entity.AuthRoleMap;
import org.snubi.auth.entity.AuthRoleMapElements;
import org.snubi.auth.entity.PagingWrapper;
import org.snubi.auth.member.repository.AuthMemberRepository;
import org.snubi.auth.member.repository.AuthMemberSetRepository;
import org.snubi.auth.member.repository.AuthRoleMapRepository;
import org.snubi.auth.member.service.AuthMemberSpecification;
import org.snubi.auth.member.service.implement.AuthMemberServiceImplement;
import org.snubi.auth.packet.AdminPacket;
import org.snubi.auth.packet.PacketAuthToken;
import org.snubi.lib.code.CodeUtil;
import org.snubi.lib.crypto.AES256Util;
import org.snubi.lib.date.DateUtil;
import org.snubi.lib.message.SMSUtil;
import org.snubi.lib.misc.Misc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings({"unchecked","rawtypes","unused"})
@RestController
@Slf4j
public class AdminController extends WithAuthController {

	@Autowired AuthMemberRepository clsAuthMemberRepository;

	@RequestMapping(value = "/admin/user/list",method = RequestMethod.GET) @ResponseBody
	public String getUserList(Pageable pageRequest
								,@RequestParam(value = "dateStart"			, required = false) String dateStart
								,@RequestParam(value = "dateTerms"			, required = false) String dateTerms
								,@RequestParam(value = "name"				, required = false) String name
								,@RequestParam(value = "title"				, required = false) String title
								,@RequestParam(value = "organizationCode"	, required = false) List<String> organizationCode
								,@RequestParam(value = "titleCode"			, required = false) List<String> titleCode) {
		AdminPacket clsAdminPacket = new AdminPacket();
		try {
			Map<String, Object> mapFilter = new HashMap<String, Object>();
			if (Misc.isEmtyString(title		) == false)	mapFilter.put("title"			, title);
			if (Misc.isEmtyString(name		) == false)	mapFilter.put("name" 			, name );
			if (Misc.isEmtyString(dateStart	) == false)	mapFilter.put("dateStart"		, dateStart.substring(0, 10) + " 00:00:00");
			if (Misc.isEmtyString(dateTerms	) == false)	mapFilter.put("dateTerms"		, dateTerms.substring(0, 10) + " 23:59:59");
			if (organizationCode 			!= null   )	mapFilter.put("organizationCode", organizationCode);
			Page<AuthMember> pageAuthMember = clsAuthMemberRepository.findAll(AuthMemberSpecification.searchFileList(mapFilter),pageRequest);
			for(AuthMember clsAuthMember : pageAuthMember) {
				clsAuthMember.decode();
				clsAuthMember.setPassword("");
			}
			clsAdminPacket.set(AuthConst.strPacketSuccessCodeVals,AuthConst.strPacketSuccessCodeVals);
			clsAdminPacket.setResultMapData((PagingWrapper<AuthMember>)this.setPagingWrapper((Page<AuthMember>)pageAuthMember));
		} catch(Exception Ex) {
//			if(AuthConst.flagDebug == true) {
//				Ex.printStackTrace();
//			}
//			log.error("Exception(getUserList) : {}",Ex.toString());
			clsAdminPacket.set(null,"예외사항이 발생되었습니다.");
		}
		return clsAdminPacket.respone();
	}
}
