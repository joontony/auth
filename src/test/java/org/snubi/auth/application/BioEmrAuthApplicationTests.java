package org.snubi.auth.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.snubi.auth.AuthConst;
import org.snubi.lib.crypto.AES256Util;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BioEmrAuthApplicationTests {

	@Test
	public void contextLoads() {
		try {
			AES256Util clsAES256Util = new AES256Util(AuthConst.strAES256Key);
			String encodedString = "2O9KazwPyZl803K9ljuWqQ==";
			String decodedString = clsAES256Util.aesDecode(encodedString);
			log.info("###### [{}->{}]",encodedString,decodedString);			
		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}
}
