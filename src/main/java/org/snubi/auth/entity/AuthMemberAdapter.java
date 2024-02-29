/**************************************************************************************** 
 * File Name    : AuthMemberSet.java
 * Function     : 
 * Author       : mh.choi
 * Tester       : 
 * Page         : 
 * Target       : 
 * Description  : 참고할 내용
				: https://stackoverflow.com/questions/4334970/hibernate-cannot-simultaneously-fetch-multiple-bags?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
				: -
				: I think a newer version of hibernate (supporting JPA 2.0) should handle this. 
				: But otherwise you can work it around by annotating the collection fields with:
				: @LazyCollection(LazyCollectionOption.FALSE)
				: Remember to remove the fetchType attribute from the @*ToMany annotation.
				: But note that in most cases a Set<Child> is more appropriate than List<Child>, 
				: so unless you really need a List - go for Set
				: + 
				: the problem is that the JPA annotations are parsed not to allow more than 2 eagerly loaded collection. 
				: But the hibernate-specific annnotations allow it.
 * Modification Log
 * =============================================================== 
 * Ver  Date        Author     Modification
 * ===============================================================
   1.0  2018.04.06  mh.choi    Create
****************************************************************************************/
package org.snubi.auth.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthMemberAdapter {
	
	public AuthMember authMemberAdapter(AuthMemberSet clsAuthMemberSet) {
		
		AuthMember clsAuthMember = new AuthMember();
		clsAuthMember.setId(clsAuthMemberSet.getId());
		clsAuthMember.setOrganizationCode(clsAuthMemberSet.getOrganizationCode());
		clsAuthMember.setDepartmentCode(clsAuthMemberSet.getDepartmentCode());
		clsAuthMember.setTitleCode(clsAuthMemberSet.getTitleCode());
		clsAuthMember.setGenderCode(clsAuthMemberSet.getGenderCode());
		clsAuthMember.setMembershipCode(clsAuthMemberSet.getMembershipCode());
		clsAuthMember.setPassword(clsAuthMemberSet.getPassword());
		clsAuthMember.setName(clsAuthMemberSet.getName());
		clsAuthMember.setEmail(clsAuthMemberSet.getEmail());
		clsAuthMember.setBirth(clsAuthMemberSet.getBirth());
		clsAuthMember.setPhone(clsAuthMemberSet.getPhone());
		clsAuthMember.setMobile(clsAuthMemberSet.getMobile());
		clsAuthMember.setCreated(clsAuthMemberSet.getCreated());
		clsAuthMember.setUpdated(clsAuthMemberSet.getUpdated());
		clsAuthMember.setCreator(clsAuthMemberSet.getCreator());
		clsAuthMember.setUpdater(clsAuthMemberSet.getUpdater());
		clsAuthMember.setValid(clsAuthMemberSet.getValid());		
		return clsAuthMember;
	}	
}
