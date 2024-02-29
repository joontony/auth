/****************************************************************************************
 * File Name    : AuthMemberDeviceRepository.java
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

import org.snubi.auth.entity.AuthMemberDevice;
import org.springframework.data.repository.CrudRepository;

public interface AuthMemberDeviceRepository extends CrudRepository<AuthMemberDevice, Long> {

	AuthMemberDevice findByIdAndSystemCode (String id,String  systemCode);
	AuthMemberDevice findByIdAndOsCodeAndSystemCode(String strId, String strOsCode, String strSystemCode);
	AuthMemberDevice findByIdAndOsCodeAndSystemCodeAndSystemTypeCode(String strId, String strOsCode,String strSystemCode, String strSystemTypeCode);

//	@Modifying
//    @Transactional
//    @Query("update AuthMemberDevice c set c.deviceId = :deviceId, c.osCode = :osCode, c.updater = :updater, c.updated = :updated  where c.id = :id and c.systemCode = :systemCode")
//	void updateDeviceIdByQuery(@Param("id") String id, @Param("systemCode") String systemCode, @Param("deviceId") String deviceId, @Param("osCode") String osCode,@Param("updater") String updater, @Param("updated") Date updated);

}
