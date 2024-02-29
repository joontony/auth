/****************************************************************************************
 * File Name    : LoggerAspect.java
 * Function     :
 * Author       : 최명호
 * Tester       :
 * Page         :
 * Target       :
 * Description  : AOP 를 이용하여 Logger 를 작성한다.
 * 				: @Around 조건이 SP버전에 따라 다르게 동작함.
 * Modification Log
 * ======================================================================================
 * Ver  	Date        Author     	Modification
 * ======================================================================================
   1.4.006  2021.12.16	최명호		파일생성
****************************************************************************************/
package org.snubi.auth.logging;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.snubi.auth.AuthConst;
import org.snubi.auth.entity.AuthLogRaw;
import org.snubi.auth.entity.AuthMember;
import org.snubi.auth.entity.AuthMemberSet;
import org.snubi.auth.stat.repository.AuthLogRawRepository;
import org.snubi.lib.code.CodeUtil;
import org.snubi.lib.date.DateUtil;
import org.snubi.lib.misc.Misc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggerAspect {

	@Autowired(required = true)
	private HttpServletRequest clsHttpServletRequest;

	@Autowired
	private AuthLogRawRepository clsAuthLogRawRepository;

	final String _USER_AGENT_ = "user-agent";
	final String _HOST_ = "host";
	final String _ID_ = "ID";

	private void saveLog(AuthLogRaw clsAuthLogRaw) {
		log.info("## saveLog  ## " + clsAuthLogRaw.toString());
		clsAuthLogRaw = clsAuthLogRawRepository.save(clsAuthLogRaw);
	}

//	DESC >> 최명호 : 관리자요청 URL - /admin/user/list
    @Around("within(org.snubi.auth.admin.controller.*)")
    public Object loggingForAdmin(ProceedingJoinPoint clsProceedingJoinPoint) throws Throwable {
    	Map<String,String> mapHeader = this.parseHeader(clsHttpServletRequest);
    	Map<String,String> mapParams = this.parseParamters(clsHttpServletRequest);
    	try {
    		this.saveLog(AuthLogRaw.builder()
							.id			(mapHeader.get(_ID_))
							.target		(mapParams.get("organizationCode"))
							.category	(CodeUtil.get("common-log_category-login").getStrCode())
							.job		(CodeUtil.get("common-log_job-search").getStrCode())
							.dateLog	(DateUtil.getThisDate())
							.method		(clsHttpServletRequest.getMethod())
							.request	(mapHeader.get(_HOST_) + clsHttpServletRequest.getRequestURI())
							.userAgent	(mapHeader.get(_USER_AGENT_))
							.ip			(clsHttpServletRequest.getRemoteAddr())
							.categorySub(null)
							.etc		(null)
							.build());
		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
    	Object result = clsProceedingJoinPoint.proceed();
        return result;
    }
//  DESC >> 최명호 : memberController
    @Around("within(org.snubi.auth.member.controller.*)")
    public Object logging2(ProceedingJoinPoint clsProceedingJoinPoint) throws Throwable {

    	try {
			Map<String,String> mapHeader = this.parseHeader(clsHttpServletRequest);
			Map<String,String> mapParams = this.parseParamters(clsHttpServletRequest);
			String id 		= mapHeader.get(_ID_);
			String target 	= "";
			String job 		= "";
			String category = "";
//			TODO >> 최명호 : 아래는 패턴들 적용하면 좋을 것 같은데..
//			TIDI >> 최명호 : 일단 아래와 같이 URL 에 따라 나열하여 처리한다.
			if(clsHttpServletRequest.getRequestURI().startsWith("/member")) {
				Map<String,String> mapForMember = this.parseMemberURL(clsHttpServletRequest,clsProceedingJoinPoint);
				target 		= mapForMember.get("target");
				job 		= mapForMember.get("job");
				category	= mapForMember.get("category");

			} else if(clsHttpServletRequest.getRequestURI().startsWith("/join")) {
				Map<String,String> mapForMember = this.parseMemberJoinURL(clsHttpServletRequest,clsProceedingJoinPoint);
				id			= mapForMember.get("target");
				target 		= mapForMember.get("target");
				job 		= mapForMember.get("job");
				category	= mapForMember.get("category");

			} else if(clsHttpServletRequest.getRequestURI().startsWith("/search")) {
				Map<String,String> mapForMember = this.parseMemberSearchURL(clsHttpServletRequest,clsProceedingJoinPoint);
				target 		= mapForMember.get("target");
				job 		= mapForMember.get("job");
				category	= mapForMember.get("category");
			}
			this.saveLog(AuthLogRaw.builder()
					.id			(id)
					.target		(target)
					.category	(category)
					.job		(job)
					.dateLog	(DateUtil.getThisDate())
					.method		(clsHttpServletRequest.getMethod())
					.request	(mapHeader.get(_HOST_) + clsHttpServletRequest.getRequestURI())
					.userAgent	(mapHeader.get(_USER_AGENT_))
					.ip			(Misc.isEmtyString(mapParams.get("ip")) == false ?  mapParams.get("ip") : clsHttpServletRequest.getRemoteAddr())
					.categorySub(null)
					.etc		(null)
					.build());

		} catch (Exception Ex) {
//			Ex.printStackTrace();
		}
    	Object result = clsProceedingJoinPoint.proceed();
        return result;
    }
//  DESC >> 최명호 : 로그인정보를 로깅한다.
    @Around("execution(* org.snubi.auth.security.service.CustomUserDetailsServiceImplement.loadUserByUsername(..))")
    public Object loggerForLogin(ProceedingJoinPoint clsProceedingJoinPoint) throws Throwable {
    	Map<String,String> mapHeader = this.parseHeader(clsHttpServletRequest);
//    	DESC >> 최명호 : 로그인-로그설정
    	try {
    		this.saveLog(AuthLogRaw.builder()
					.id			(String.valueOf(clsProceedingJoinPoint.getArgs()[0]))
					.target		(String.valueOf(clsProceedingJoinPoint.getArgs()[0]))
					.category	(CodeUtil.get("common-log_category-login").getStrCode())
					.job		(CodeUtil.get("common-log_job-search").getStrCode())
					.dateLog	(DateUtil.getThisDate())
					.method		(clsHttpServletRequest.getMethod())
					.request	(mapHeader.get(_HOST_) + clsHttpServletRequest.getRequestURI())
					.userAgent	(mapHeader.get(_USER_AGENT_))
					.ip			(clsHttpServletRequest.getRemoteAddr())
					.categorySub(null)
					.etc		(null)
					.build());
		} catch (Exception Ex) {
//			Ex.printStackTrace();
		}
    	Object result = clsProceedingJoinPoint.proceed();
        return result;
    }
//	DESC >> 최명호 : 사용자 Token 존재하며, 아래 URL 을 처리함.
//	@RequestMapping(value = "/member/doctor/{id}",method = RequestMethod.GET) @ResponseBody
//	@RequestMapping(value = "/member/{id}",method = RequestMethod.GET) @ResponseBody
//	@RequestMapping(value = "/member/list/{system}/{organization}/{department}",method = RequestMethod.GET) @ResponseBody
//	@RequestMapping(value = "/member",method = RequestMethod.DELETE) @ResponseBody/
//	@RequestMapping(value = "/member",method = RequestMethod.PUT) @ResponseBody
    public Map<String,String> parseMemberURL(HttpServletRequest clsHttpServletRequest,ProceedingJoinPoint clsProceedingJoinPoint) {
    	Map<String,String> mapForResult = new HashMap<String,String>();
		try {
			String target 	= clsHttpServletRequest.getRequestURI().replace("/member", "");
			String category	= (CodeUtil.get("common-log_category-user").getStrCode());
			String job 		= (CodeUtil.get("common-log_job-search").getStrCode());

			switch(clsHttpServletRequest.getMethod().charAt(0)) {
				case 'D' :	job	= (CodeUtil.get("common-log_job-delete").getStrCode());
							target = ((AuthMember) (clsProceedingJoinPoint.getArgs())[0]).getId();
							break;
				case 'P' :	job	= (CodeUtil.get("common-log_job-update").getStrCode());
							target = ((AuthMemberSet) (clsProceedingJoinPoint.getArgs())[0]).getId();
							break;
			}
			mapForResult.put("target"	, target	);
			mapForResult.put("job"		, job		);
			mapForResult.put("category"	, category	);
			return mapForResult;
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
    }
//	DESC >> 최명호 : 사용자 Token 존재하지않으며, 아래 URL 을 처리함.
//	@RequestMapping(value = "/join",method = RequestMethod.POST) @ResponseBody
    public Map<String,String> parseMemberJoinURL(HttpServletRequest clsHttpServletRequest,ProceedingJoinPoint clsProceedingJoinPoint) {
    	Map<String,String> mapForResult = new HashMap<String,String>();
		try {
			mapForResult.put("target"	, ((AuthMemberSet) (clsProceedingJoinPoint.getArgs())[0]).getId());
			mapForResult.put("job"		, CodeUtil.get("common-log_category-user").getStrCode());
			mapForResult.put("category"	, CodeUtil.get("common-log_job-create").getStrCode());
			return mapForResult;
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
    }
//	DESC >> 최명호 : 사용자 Token 존재하며  아래 URL 을 처리함.
//	@RequestMapping(value = "/search/id/{id}",method = RequestMethod.GET) @ResponseBody
//	@RequestMapping(value = "/search/password/{id}/{mobile}/{name}",method = RequestMethod.GET) @ResponseBody
    public Map<String,String> parseMemberSearchURL(HttpServletRequest clsHttpServletRequest,ProceedingJoinPoint clsProceedingJoinPoint) {
    	Map<String,String> mapForResult = new HashMap<String,String>();
		try {
			mapForResult.put("target"	, clsHttpServletRequest.getRequestURI().replace("/search", ""));
			mapForResult.put("job"		, CodeUtil.get("common-log_category-user").getStrCode());
			mapForResult.put("category"	, CodeUtil.get("common-log_job-search").getStrCode());
			return mapForResult;
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
    }
//  DESC >> 최명호 : 아래정보는 굳이 처리할 필요가 없을것 같다. 사용자 Token 은 없음.
//	@RequestMapping(value = "/find/password/{id}/{mobile}/{name}",method = RequestMethod.GET) @ResponseBody
//	@RequestMapping(value = "/find/password",method = RequestMethod.GET) @ResponseBody
//	@RequestMapping(value = "/find/id/email/{id}/{email:.+}",method = RequestMethod.GET) @ResponseBody
//	@RequestMapping(value = "/find/mobile/{mobile}/{name}",method = RequestMethod.GET) @ResponseBody
//	@RequestMapping(value = "/find/email",method = RequestMethod.GET) @ResponseBody
//	@RequestMapping(value = "/find/email/{email}",method = RequestMethod.GET) @ResponseBody
//	@RequestMapping(value = "/find/id/{id}",method = RequestMethod.GET) @ResponseBody
//	@RequestMapping(value = "/find/list/{system}/{organization}/{department}",method = RequestMethod.GET) @ResponseBody
    public Map<String,String> parseMemberFindURL(HttpServletRequest clsHttpServletRequest,ProceedingJoinPoint clsProceedingJoinPoint) {
    	return null;
    }

//  TODO >> 최명호 : library 항목으로 분리해야 할듯 하다.
	public String getIDFromToken(String token) {
		try {
//			JWTClaims clsJWTClaims = (new JTWClaimsUtil()).getClaims(token,key);
			Claims clsClaims = Jwts.parser().setSigningKey(AuthConst.strSecrete.getBytes()).parseClaimsJws(token.replace(AuthConst.strTokenPrefix, "")).getBody();
			return clsClaims.getSubject();
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
	}
//  TODO >> 최명호 : library 항목으로 분리해야 할듯 하다.
	public Map<String,String> parseParamters(HttpServletRequest clsHttpServletRequest) {
		Map<String,String> mapForResult = new HashMap<String,String>();
		try {
			Enumeration<String> parameterNames = clsHttpServletRequest.getParameterNames();
			while(parameterNames.hasMoreElements()) {
				String parameterName = (String)parameterNames.nextElement();
				mapForResult.put(parameterName, clsHttpServletRequest.getParameter(parameterName));
			}
			return mapForResult;
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
	}
//  TODO >> 최명호 : library 항목으로 분리해야 할듯 하다.
	public Map<String,String> parseHeader(HttpServletRequest clsHttpServletRequest) {
		Map<String,String> mapForResult = new HashMap<String,String>();
		try {
			Enumeration<String> headerNames = clsHttpServletRequest.getHeaderNames();
			while(headerNames.hasMoreElements()) {
				String headerName = (String)headerNames.nextElement();
				mapForResult.put(headerName, clsHttpServletRequest.getHeader(headerName));
			}
			if(mapForResult.get("authorization") != null) {
				String id = this.getIDFromToken(mapForResult.get("authorization"));
				if(Misc.isEmtyString(id) == false) {
					mapForResult.put(_ID_,id);
					mapForResult.put("authorization","");
				}
			}

		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}

		return mapForResult;
	}
}