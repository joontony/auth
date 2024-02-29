/****************************************************************************************
 * File Name    : AuthLogRawRepository.java
 * Function     :
 * Author       : mh.choi
 * Tester       :
 * Page         :
 * Target       :
 * Description  :
 * Modification Log
 * ======================================================================================
 * Ver  Date        Author     	Modification
 * ======================================================================================
   1.0 	2018.04.30	최명호		파일생성
****************************************************************************************/
package org.snubi.auth.stat.repository;

import java.util.List;

import org.snubi.auth.entity.AuthLogRaw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthLogRawRepository extends JpaRepository<AuthLogRaw, Long> {

	@Query(value = " 	select 	a.id,a.email,a.updated,a.valid,b.role_code,b.system_code " +
				"		from 	tb_auth_member a, " +
				"				tb_auth_member_role b " +
				"		where 	a.id not in ( " +
				"			select 	distinct id " +
				"			from 	tb_auth_log_raw " +
				"			where 	date_log >= date_add(now(), interval -180 day) " +
				"		) " +
				"		and a.valid = 'active'  " +
				"		and b.system_code = 'platform' " +
				"		and b.role_code = 'admin' " +
				"		and a.id = b.id;"
	,nativeQuery = true)
	List<Object[]> findIdleUser();

}
