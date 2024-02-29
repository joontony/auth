/****************************************************************************************
 * File Name    : AuthMemberSpecification.java
 * Function     :
 * Author       : mh.choi
 * Tester       :
 * Page         :
 * Target       :
 * Description  :
 * Modification Log
 * ======================================================================================
 * Ver  Date        Author     Modification
 * ======================================================================================
   1.0  2019.12.12	최명호		파일생성
   1.1	2020.02.10	최명호		기관코드조건추가
****************************************************************************************/
package org.snubi.auth.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;

import org.snubi.auth.entity.AuthMember;
import org.snubi.lib.date.DateUtil;
import org.springframework.data.jpa.domain.Specification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthMemberSpecification {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Specification<AuthMember> searchFileList(Map<String, Object> mapFilter) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList();
			mapFilter.forEach((key, value) -> {
				log.info("Specification [{}->{},{}]",key,value,value.getClass());
				switch(key) {
					case "name"  			:	predicates.add(criteriaBuilder.like 				(root.get(key).as(String.class), "%" + value + "%"));							break;
					case "dateStart" 		:	predicates.add(criteriaBuilder.greaterThanOrEqualTo	(root.get("created"), DateUtil.toDate("yyyy-MM-dd mm:hh:ss",(String) value)));	break;
					case "dateTerms"		:	predicates.add(criteriaBuilder.lessThanOrEqualTo	(root.get("created"), DateUtil.toDate("yyyy-MM-dd mm:hh:ss",(String) value)));	break;
					case "organizationCode"	:
					case "titleCode"		:	In<String> inClause = criteriaBuilder.in(root.get(key).as(String.class));
												for (String eachValue : (List<String>) value) {
												    inClause.value(eachValue);
												}
												predicates.add(inClause);
												break;
					default	:					predicates.add(criteriaBuilder.equal(root.get(key).as(String.class),value));
												break;
				}
			});
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}