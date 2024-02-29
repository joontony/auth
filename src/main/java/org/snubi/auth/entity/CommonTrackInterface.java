/**************************************************************************************** 
 * File Name    : CommonTrackInterface.java
 * Function     : 
 * Author       : mh.choi
 * Tester       : 
 * Page         : 
 * Target       : 
 * Description  : 테이블 Track에 관련된 공통항목을 정의함
 * Modification Log
 * =============================================================== 
 * Ver  Date        Author     Modification
 * ===============================================================
   1.0  2018.05.15  mh.choi    Create
****************************************************************************************/
package org.snubi.auth.entity;

import java.util.Date;

public interface CommonTrackInterface {

	public String getValid();
	public String getCreator();
	public Date getCreated();
	public String getUpdater();
	public Date getUpdated();
	public void setValid(String valid) ;
	public void setCreator(String creator);
	public void setCreated(Date created) ;
	public void setUpdater(String updater);
	public void setUpdated(Date updated) ;
}
