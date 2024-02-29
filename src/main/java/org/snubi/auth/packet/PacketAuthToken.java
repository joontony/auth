/****************************************************************************************
 * File Name    : BioEmrPacketJoin.java
 * Function     :
 * Author       : 최명호
 * Tester       :
 * Page         :
 * Target       :
 * Description  :
 * Modification Log
 * ======================================================================================
 * Ver  	Date        Author     Modification
 * ======================================================================================
   1.0  	2018.05.17  최명호    	Create
   1.4.004  2018.09.18  최명호    	비밀번호찾기시 비밀번호를 전달하지 않도록 함.
****************************************************************************************/
package org.snubi.auth.packet;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.snubi.auth.entity.AuthMember;
import org.snubi.auth.entity.AuthMemberSet;
import org.snubi.auth.member.service.AuthMemberService;
import org.snubi.lib.misc.Misc;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@SuppressWarnings({"unchecked","rawtypes"})
public class PacketAuthToken<T> extends PacketAuth<T> {

	AuthMemberService clsMemberServiceImplement;

	public void setService(AuthMemberService clsMemberServiceImplement) {
		this.clsMemberServiceImplement = clsMemberServiceImplement;
	}
	@Override
	public String respone() {
		return new JSONObject(mapData).toString();
	}
	public String responeWithOnlyId() {
		AuthMemberSet clsAuthMemberSet = (AuthMemberSet) mapData.get("data");
		if(clsAuthMemberSet != null) {
			mapData.put("data",Misc.asteriskString(2,1,clsAuthMemberSet.getId(),'*'));
		} else {
			mapData.put("data","");
		}
		return new JSONObject(mapData).toString();
	}
	@SuppressWarnings("unused")
	public String responeWithOnlyPassword() {
		AuthMemberSet clsAuthMemberSet = (AuthMemberSet) mapData.get("data");
		clsAuthMemberSet.decode();
		if(clsAuthMemberSet != null) {
			mapData.put("data",Misc.asteriskString(1,1,clsAuthMemberSet.getPassword(),'*'));
		} else {
			mapData.put("data","");
		}
		return new JSONObject(mapData).toString();
	}
	public String responeWithOnlyEmail() {
		AuthMember clsAuthMember = (AuthMember) mapData.get("data");
		if(clsAuthMember != null) {
			mapData.put("data",clsAuthMember.getEmail());
		} else {
			mapData.put("data","");
		}
		return new JSONObject(mapData).toString();
	}
	public String responeWithOnlyPasswordAll() {
		AuthMember clsAuthMember = (AuthMember) mapData.get("data");
		if(clsAuthMember != null) {
//			mapData.put("data",clsAuthMember.getPassword());
			mapData.put("data","제공않됨");
		} else {
			mapData.put("data","");
		}
		return new JSONObject(mapData).toString();
	}
	@Override
	boolean post(T clsParameter) throws Exception {
		AuthMemberSet clsAuthMemberSet = (AuthMemberSet) this.object;
		clsAuthMemberSet.encode();
		return clsMemberServiceImplement.save(clsAuthMemberSet);
	}
	@Override
	boolean get(T clsParameter) throws Exception {
		if(clsParameter == AuthMemberSet.class) {
			if(this.object != null) {
				AuthMemberSet clsAuthMemberSet = (AuthMemberSet) this.object;
				clsAuthMemberSet.decode();
				clsAuthMemberSet.setPassword("");
				mapData.put("data",clsAuthMemberSet);
			} else {
				mapData.put("data",null);
			}
			return true;

		} else if(clsParameter == AuthMember.class) {
			if(this.object != null) {
				AuthMember clsAuthMember = (AuthMember) this.object;
				clsAuthMember.decode();
				mapData.put("data",clsAuthMember);
			} else {
				mapData.put("data",null);
			}
			return true;

		} else if(clsParameter == HashMap.class) {
			Page<AuthMemberSet> pageAuthMemberSet = (Page<AuthMemberSet>) clsMemberServiceImplement.list((T)this.object);
			for(AuthMemberSet clsAuthMemberSet : pageAuthMemberSet) {
				clsAuthMemberSet.decode();
				clsAuthMemberSet.setPassword("");
			}
			mapData.put("data",pageAuthMemberSet);
			return true;

		} else if (clsParameter == java.util.ArrayList.class) {
			List<T> listData = (List<T>) this.object;
			mapData.put("data",listData);
		}
		return false;
	}
	@Override
	boolean put(T clsParameter) throws Exception {
		AuthMemberSet clsAuthMemberSet = (AuthMemberSet) this.object;
		clsAuthMemberSet.encode();
		return clsMemberServiceImplement.update(clsAuthMemberSet);
	}
	@Override
	boolean delete(T clsParameter) throws Exception {
		AuthMemberSet clsAuthMemberSet = (AuthMemberSet) this.object;
		clsAuthMemberSet.encode();
		return clsMemberServiceImplement.delete(clsAuthMemberSet);
	}
	public boolean isThere() {
		try {
			return mapData.get("data") != null;
		} catch(Exception Ex) {
			return false;
		}
	}
}
