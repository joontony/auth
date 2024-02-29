/****************************************************************************************
 * File Name    : StatMakeRaw.java
 * Function     :
 * Author       : mh.choi
 * Tester       :
 * Page         :
 * Target       :
 * Description  : 통계용Raw자료를 생성함
 * Modification Log
 * ======================================================================================
 * Ver  Date        Author     	Modification
 * ======================================================================================
   1.0  2019.04.30	최명호		파일생성
****************************************************************************************/
package org.snubi.auth.stat;

import org.snubi.auth.entity.AuthLogRaw;
import org.snubi.auth.stat.service.AuthLogRawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;


@Component
@NoArgsConstructor
public class StatMakeRaw  {

	@Autowired
	AuthLogRawService clsAuthLogRawService;
	public void make(AuthLogRaw clsAuthLogRaw) throws Exception {
		clsAuthLogRawService.save(clsAuthLogRaw);
	}
}
