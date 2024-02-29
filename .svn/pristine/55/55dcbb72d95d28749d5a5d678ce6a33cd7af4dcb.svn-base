/**************************************************************************************** 
 * File Name    : MemberControllerTest.java
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
   1.0  2018.04.13  mh.choi    Create
****************************************************************************************/
package org.snubi.auth.member;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.snubi.auth.entity.AuthMemberDevice;
import org.snubi.auth.entity.AuthMemberRole;
import org.snubi.auth.entity.AuthMemberSet;
import org.snubi.auth.member.controller.AuthMemberController;
import org.snubi.lib.date.DateUtil;
import org.snubi.lib.json.JsonUtil;
import org.snubi.lib.response.json.JsonToSnubiResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberControllerTest { 
 
    @Autowired
    private AuthMemberController controllerToTest;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerToTest).build();
    }
    @Test
    public void testSignUp() throws Exception{
    	
    	controllerToTest = mock(AuthMemberController.class);
    	
		AuthMemberSet clsAuthMemberSet = new AuthMemberSet();
		AuthMemberDevice clsAuthMemberDevice = new AuthMemberDevice();
		AuthMemberRole clsAuthMemberRole = new AuthMemberRole();
    	
    	String strID = "test005";
    	clsAuthMemberSet.setId	(strID);
    	clsAuthMemberSet.setBirth(DateUtil.getThisSQLDate());
    	clsAuthMemberSet.setDepartmentCode("부서0001");
    	clsAuthMemberSet.setEmail("xanitus@nate.com");
    	clsAuthMemberSet.setGenderCode("M");
    	clsAuthMemberSet.setMembershipCode("V");
    	clsAuthMemberSet.setName("최명호001");
    	clsAuthMemberSet.setOrganizationCode("병원001");
    	clsAuthMemberSet.setPassword("password");
    	clsAuthMemberSet.setTitleCode("직책0001");
    	
    	List<AuthMemberDevice> listAuthMemberDevice = new ArrayList<AuthMemberDevice>();    	
    	clsAuthMemberDevice.setId(strID);
    	clsAuthMemberDevice.setDeviceId("device001");
    	clsAuthMemberDevice.setMobile("01031764592");
    	clsAuthMemberDevice.setSystemCode("system001");
    	listAuthMemberDevice.add(clsAuthMemberDevice);
    	clsAuthMemberSet.setListAuthMemberDevice(listAuthMemberDevice);
    	
    	List<AuthMemberRole> listAuthMemberRole = new ArrayList<AuthMemberRole>();
    	clsAuthMemberRole.setId(strID);
    	clsAuthMemberRole.setRoleCode("role001");
    	clsAuthMemberSet.setListAuthMemberRole(listAuthMemberRole);
    	
    	JsonUtil<AuthMemberSet> clsJsonUtil = new JsonUtil<AuthMemberSet>();
    	String strBuffer = clsJsonUtil.toString(clsAuthMemberSet);
    	MvcResult result = this.mockMvc        		
				.perform(post("/member").header("Content-Type", "application/json").characterEncoding("UTF-8").content(strBuffer))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
    	
    	String content = result.getResponse().getContentAsString();
    	JsonToSnubiResponseResult clsJsonToSnubiResponseResult = new JsonToSnubiResponseResult();
	    clsJsonToSnubiResponseResult.get(content);
	    clsJsonToSnubiResponseResult.getClsSnubiResponse().print();
    }
}
