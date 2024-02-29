/**************************************************************************************** 
 * File Name    : BioEmrPacket.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.snubi.auth.AuthConst;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.Getter;
import lombok.Setter;

@Component
public abstract class PacketAuth<T> {
	
	protected final String packetCodeName 			= AuthConst.strPacketCodeName			;
	protected final String packetMessageName 		= AuthConst.strPacketMessageName		;
	protected final String packetTokenName 			= AuthConst.strPacketTokenName			;
	protected final String packetDataName 			= AuthConst.strPacketDataName			;
	protected final String packetErrorCodeVal 		= AuthConst.strPacketErrorCodeVals		;
	protected final String packetErrorMessageVal 	= AuthConst.strPacketErrorMessageVals	;
	protected final String packetSuccessCodeVal 	= AuthConst.strPacketSuccessCodeVals	;
	protected final String packetSuccessMessageVal 	= AuthConst.strPacketSuccessMessageVals	;
	protected JSONObject 			jSonObject		;
	protected Object 				object			;
	protected Map<String, Object> 	mapData 		= new HashMap<String, Object>()	;
	protected ArrayList<ReqParams> 	reqParamList 	= new ArrayList<ReqParams>()	;
	protected T						clsParameter 	= null							;
	
	public abstract String respone();
	abstract boolean post	(T clsParameter) throws Exception;
	abstract boolean get 	(T clsParameter) throws Exception;
	abstract boolean put 	(T clsParameter) throws Exception;
	abstract boolean delete	(T clsParameter) throws Exception;
	
	public Map<String, Object> setResultMapData(Object objectData) {
		this.mapData.put("data",objectData);
		return this.mapData;
	}
	public Object getResultMapData() {
		return this.mapData.get("data");
	}
	public Map<String, Object> createResultMap() {
		mapData.put("code"		, this.packetSuccessCodeVal);
		mapData.put("message"	, this.packetSuccessMessageVal);
		mapData.put("data"		, ""); 
		mapData.put("token"		, ""); 
		return mapData; 
	}
	public ArrayList<ReqParams> createReqestMap() {
		return null;
	} 
	protected Iterator<ReqParams> getParamIterator() {
		Iterator<ReqParams> iterator = reqParamList.iterator();
		return iterator;
	}
    public ArrayList<ReqParams> parse(HttpServletRequest requestOrg) {
		return null;
	}
	public ArrayList<ReqParams> get(HttpServletRequest requestOrg) throws Exception {
		return this.parse(requestOrg); 
	}
	public boolean set(T clsParameter, Object object ,RequestMethod reqMethod) throws Exception {		
		createResultMap();
		this.clsParameter = clsParameter;
		this.object = object;
		mapData.put(this.packetCodeName, this.packetSuccessCodeVal);
	    mapData.put(this.packetMessageName, this.packetSuccessMessageVal);
	    switch(reqMethod) { 
	    	case POST	: return this.post	(clsParameter);
	    	case PUT 	: return this.put	(clsParameter);
	    	case GET 	: return this.get	(clsParameter);
	    	case DELETE : return this.delete(clsParameter);
	    	default		: return false;
	    }
	}	
	public void set(String code, String message) {
		this.mapData.put(this.packetCodeName, code == null ? this.packetErrorCodeVal : code);
		this.mapData.put(this.packetMessageName, message == null ? this.packetErrorMessageVal : message);
	}
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from : ").append(jSonObject.toString()).append("\n");
		for(String key : mapData.keySet()) {
			stringBuffer.append("key : ").append(key).append(",value : ").append(mapData.get(key)).append("\n");
		}
		return stringBuffer.toString();
	}
	@Getter
	@Setter
	public class ReqParams {
		String name = "";
		Boolean mandatory = false;
		int min = 0;
		int max = 0;
		public ReqParams(String name,Boolean mandatory,int min,int max) {
			this.name = name;
			this.mandatory = mandatory;
			this.min = min;
			this.max = max;
			reqParamList.add(this);
		}
	}
}
