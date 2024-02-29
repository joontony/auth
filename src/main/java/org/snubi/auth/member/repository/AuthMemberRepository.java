/****************************************************************************************
 * File Name    : AuthMemberRepository.java
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
   1.0  2018.04.06  mh.choi    	Create
   1.1  2019.06.07	최명호		사용하지 않는 함수 제거함 List<AuthMember> findCountMemberGroupByOrganization();
   1.2  2019.07.15  mh.choi    	Auth Member  업데이트항목 수정
****************************************************************************************/
package org.snubi.auth.member.repository;

import java.util.Date;

import org.snubi.auth.entity.AuthMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AuthMemberRepository extends CrudRepository<AuthMember, String> {
	AuthMember findById(String id);
	AuthMember findByNameAndGenderCodeAndBirth(String name,String genderCode,Date birth);
	AuthMember findByNameAndGenderCodeAndEmail(String name,String genderCode,String email);
	AuthMember findByEmail(String email);
	AuthMember findByNameAndGenderCode(String name, String genderCode);
	AuthMember findByPhone(String phone);
	AuthMember findByMobile(String mobile);

	@Modifying
    @Transactional
	@Query("update AuthMember c set c.name = :name, c.password = :password, c.cryptographicAlgorithm = :cryptographicAlgorithm, c.birth = :birth, c.phone = :phone, c.genderCode = :gender, c.updater = :updater, c.updated = :updated  where c.id = :id")
	void updateByQuery	(@Param("id"					) String 	id
						,@Param("name"					) String 	name
						,@Param("password"				) String 	password
						,@Param("cryptographicAlgorithm") String 	cryptographicAlgorithm
						,@Param("birth"					) Date 		birth
						,@Param("phone"					) String 	phone
						,@Param("gender"				) String 	gender
						,@Param("updater"				) String 	updater
						,@Param("updated"				) Date 		updated
						);
	Page<AuthMember> findAll(Specification<AuthMember> searchFileList, Pageable pageRequest);
	AuthMember findOneByIdAndMobileAndName(String id, String mobile, String name );

}
