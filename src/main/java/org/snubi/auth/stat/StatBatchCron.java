/****************************************************************************************
 * File Name    : StatBatchCron.java
 * Function     :
 * Author       : mh.choi
 * Tester       :
 * Page         :
 * Target       :
 * Description  : 연월일시분초 반대 순으로 @Scheduled(cron = "0 30 5 * * *")
 * Modification Log
 * ======================================================================================
 * Ver  Date        Author     	Modification
 * ======================================================================================
   1.0  2019.04.30	최명호		파일생성
****************************************************************************************/
package org.snubi.auth.stat;

import org.snubi.auth.stat.service.AuthLogRawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StatBatchCron {

	@Autowired
	AuthLogRawService clsAuthLogRawService;

    @Scheduled(initialDelay = 0, fixedDelay = 120000)
    public void otherJob() {
    	try {
			clsAuthLogRawService.state(null);
		} catch (Exception Ex) {
			log.debug("## StatBatchCron ## ");
		}
    }
}