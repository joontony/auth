/**************************************************************************************** 
 * File Name    : BioEmrPacketAuth.java
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
   1.0  2018.04.09  mh.choi    Create
****************************************************************************************/
package org.snubi.auth.packet;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacketAuthGetToken<T> extends PacketAuth<T>  {
	public PacketAuthGetToken() {	
		createReqestMap();
		createResultMap();
	}
	@Override
	public String respone() {
		return new JSONObject(mapData).toString();
	}
	@Override
	boolean post(T clsParameter) throws Exception {
    	mapData.put(this.packetTokenName, "" +  (String) this.object);		
		return true;
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
}
