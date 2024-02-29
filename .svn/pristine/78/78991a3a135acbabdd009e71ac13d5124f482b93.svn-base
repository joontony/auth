/**************************************************************************************** 
 * File Name    : AuthStatDDRepository.java
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

import org.snubi.auth.entity.AuthStatDD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AuthStatDDRepository extends JpaRepository<AuthStatDD, Long> {
	
	@Modifying
	@Transactional
	@Query("delete from AuthStatDD c where c.statYy = :statYy and c.statMm = :statMm and c.statDd = :statDd ")
	void deleteByQueryWithYMD(@Param("statYy") long statYy, @Param("statMm") long statMm, @Param("statDd") long statDd);	
	
}
