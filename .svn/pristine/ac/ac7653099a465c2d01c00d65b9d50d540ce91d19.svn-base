/**************************************************************************************** 
 * File Name    : AuthRoleMapRepository.java
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

import org.snubi.auth.entity.AuthRoleMap;
import org.springframework.data.repository.CrudRepository;

public interface AuthRoleMapRepository extends CrudRepository<AuthRoleMap, String> {
	AuthRoleMap findOneBySystemCodeAndRoleCode(String systemCode, String roleCode);
}
