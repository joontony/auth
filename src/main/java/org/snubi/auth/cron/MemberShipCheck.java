/****************************************************************************************
 * File Name    : MemberShipCheck.java
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
   1.0  2021.12.26	최명호		파일생성
****************************************************************************************/
package org.snubi.auth.cron;

import java.net.InetAddress;
import java.util.List;

import org.snubi.auth.entity.AuthLogRaw;
import org.snubi.auth.entity.AuthMember;
import org.snubi.auth.member.repository.AuthMemberRepository;
import org.snubi.auth.stat.repository.AuthLogRawRepository;
import org.snubi.lib.code.CodeUtil;
import org.snubi.lib.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MemberShipCheck {

	@Autowired
	AuthLogRawRepository clsAuthLogRawRepository;

	@Autowired
	AuthMemberRepository clsAuthMemberRepository;

//	DESC >> 최명호 : 6개월동안 접속하지 않는 사용자를 invalid 되도록 한다.
//	TODO >> 최명호 : update 쿼리로 수행하지 않고, 로그를 작성하기 위해서..아래와 같이 처리한다.
//	TODO >> 최명호 : transaction 는 고려하지 않는다. 대상자 아이디의 개수를 고려하지 않는다.
	@Scheduled(cron = "0 0 5 * * *")
    public void checkMemberShip() {
    	try {
			List<Object[]> listObjects = clsAuthLogRawRepository.findIdleUser();
			for(Object[] objects : listObjects) {
				String id = String.valueOf(objects[0]);
				log.info("user [{}] deactive, email : {}",id,String.valueOf(objects[1]));
				AuthMember clsAuthMember = clsAuthMemberRepository.findById(id);
				clsAuthMember.setUpdated(DateUtil.getThisDate());
				clsAuthMember.setValid(CodeUtil.get("deactive").getStrCode());
				clsAuthMember = clsAuthMemberRepository.save(clsAuthMember);
//				DESC >> 최명호 : 오류는 무시한다. 따라서 Transaction 으로 묶지 않는다.
				try {
					AuthLogRaw clsAuthLogRaw = AuthLogRaw.builder()
							.id			("deamon")
							.target		(id)
							.category	(CodeUtil.get("common-log_category-user").getStrCode())
							.job		(CodeUtil.get("common-log_job-delete").getStrCode())
							.dateLog	(DateUtil.getThisDate())
							.method		("CRON")
							.request	("scheduled")
							.userAgent	("server")
							.ip			(InetAddress.getLocalHost().getHostAddress())
							.categorySub(null)
							.etc		(null)
							.build();
					clsAuthLogRawRepository.save(clsAuthLogRaw);
				} catch (Exception e) {
					log.error("로그작업을 수행할 수 없습니다.");
				}
			}
		} catch (Exception Ex) {
			log.error("## checkMemberShip ## {}",Ex.toString());
		}
    }
}