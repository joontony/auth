/****************************************************************************************
 * File Name    : AuthMemberSetRepository.java
 * Function     :
 * Author       : mh.choi
 * Tester       :
 * Page         :
 * Target       :
 * Description  :
 * Modification Log
 * ===============================================================
 * Ver  Date        Author     Modification
 * ===============================================================
   1.0  2018.04.06  mh.choi    Create
****************************************************************************************/
package org.snubi.auth.member.repository;

import java.util.List;

import org.snubi.auth.entity.AuthMember;
import org.snubi.auth.entity.AuthMemberSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthMemberSetRepository extends JpaRepository<AuthMemberSet, String> {
	AuthMemberSet 		findById												(String strId			);
	AuthMemberSet 		findOneByName											(String name		 	);
	AuthMemberSet 		findOneByMobile											(String mobile		 	);
	AuthMemberSet 		findOneByMobileAndName									(String mobile		 	, String name			);
	AuthMemberSet 		findOneByIdAndMobile									(String id				, String mobile		 	);
	AuthMemberSet 		findOneByEmailAndPassword								(String email			, String password		);
	AuthMemberSet 		findOneByIdAndEmail										(String id			 	, String email			);
	List<AuthMember> 	findByMobileAndName										(String mobile		 	, String name			);
	AuthMemberSet 		findOneByIdAndMobileAndName								(String id				, String mobile		 	, String name			);
	List<AuthMemberSet> findOneByOrganizationCodeAndDepartmentCodeAndTitleCode	(String strOrganization	, String strDepartment	, String titleCode		);
	Page<AuthMemberSet> findAllByOrganizationCodeAndDepartmentCode				(Pageable pageRequest	, String strOrganization, String strDepartment	);

	@Query("SELECT new AuthMemberSet (count(*), a.organizationCode)  FROM AuthMemberSet a GROUP BY a.organizationCode")
	List<AuthMemberSet> countByIdGroupByOrganization();

	@Query("SELECT new AuthMemberSet (count(*), a.organizationCode, a.departmentCode) FROM AuthMemberSet a GROUP BY a.organizationCode, a.departmentCode")
	List<AuthMemberSet> countByIdGroupByDepartmentCode();
	AuthMemberSet findOneByEmail(String strEmail);

}
