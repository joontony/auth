/****************************************************************************************
 * File Name    : AdminPacket.java
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
   1.0  2018.05.17  mh.choi    Create
****************************************************************************************/
package org.snubi.auth.packet;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class AdminPacket<T> extends PacketAuth<T> {

	@Override
	boolean post(T clsParameter) throws Exception {
		return false;
	}
	@Override
	boolean get(T clsParameter) throws Exception {
		return false;
	}
	@Override
	boolean put(T clsParameter) throws Exception {
		return false;
	}
	@Override
	boolean delete(T clsParameter) throws Exception {
		return false;
	}
	@Override
	public String respone() {
		return new JSONObject(mapData).toString();
	}
}
