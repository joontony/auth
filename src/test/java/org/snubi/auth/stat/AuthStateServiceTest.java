/**************************************************************************************** 
 * File Name    : AuthStateServiceTest.java
 * Function     : 
 * Author       : mh.choi
 * Tester       : 
 * Page         : 
 * Target       : 
 * Description  : 
 * Modification Log
 * =============================================================== 
 * Ver  Date        Author     	Modification
 * ===============================================================
   1.0  2019.04.30	최명호		통계모듈생성
****************************************************************************************/
package org.snubi.auth.stat;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.snubi.auth.entity.AuthStatDD;
import org.snubi.auth.stat.repository.AuthLogRawRepository;
import org.snubi.auth.stat.repository.AuthStatDDRepository;
import org.snubi.lib.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthStateServiceTest {

	@Autowired
	AuthStatDDRepository clsAuthStatDDRepository;
	
	@Autowired
	AuthLogRawRepository clsAuthLogRawRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	public void insertAuthStat() {	
		
		System.out.println("##################### insertAuthStat start ");
		StringBuffer bufferSql = new StringBuffer();
		String	 thisDay 	= DateUtil.getThisDateString("yyyy-MM-dd");
		String 	 prevDay 	= DateUtil.getDateCalculated(DateUtil.getThisDate(), -1, "yyyy-MM-dd");
		String[] arrThisDy 	= thisDay.split("-");
		String[] arrPrevDay = prevDay.split("-");
		clsAuthStatDDRepository.deleteByQueryWithYMD(Long.parseLong(arrThisDy[0]),Long.parseLong(arrThisDy[1]),Long.parseLong(arrThisDy[2]));
		clsAuthStatDDRepository.deleteByQueryWithYMD(Long.parseLong(arrPrevDay[0]),Long.parseLong(arrPrevDay[1]),Long.parseLong(arrPrevDay[2]));
		bufferSql.append("SELECT 	count(c.seq) as total,								");
		bufferSql.append("			m.genderCode as gender,								");
		bufferSql.append("			ceil((to_days(now())-to_days(m.birth))/365) as age,	");
		bufferSql.append("			date_format(c.dateLog,'%Y') as stat_yy,				");
		bufferSql.append("			date_format(c.dateLog,'%m') as stat_mm,				");
		bufferSql.append("			date_format(c.dateLog,'%d') as stat_dd				");
		bufferSql.append("  FROM 	AuthLogRaw c, AuthMember m 							");
		bufferSql.append(" WHERE 	c.id = m.id 										");
		bufferSql.append("   AND 	c.dateLog >= '").append(prevDay).append(" 00:00:00'	");
		bufferSql.append("   AND 	c.dateLog <= '").append(thisDay).append(" 23:59:59'	");
		bufferSql.append(" GROUP	BY 													");
		bufferSql.append(" 			m.genderCode,										");
		bufferSql.append("			ceil((to_days(now())-to_days(m.birth))/365),		");
		bufferSql.append("			date_format(c.dateLog,'%Y'),						");
		bufferSql.append("			date_format(c.dateLog,'%m'),						");
		bufferSql.append("			date_format(c.dateLog,'%d')							");
		System.out.println(bufferSql.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> listObjects  = entityManager.createQuery(bufferSql.toString()).getResultList();
		for(Object[] objects : listObjects) {
        	AuthStatDD clsAuthStatDD = new AuthStatDD();
        	clsAuthStatDD.setCategory	("REQ"								);
        	clsAuthStatDD.setTotal		(Long.parseLong((String.valueOf(objects[0]))));
        	clsAuthStatDD.setGender		(			 	(String.valueOf(objects[1])));
        	clsAuthStatDD.setAge		(Long.parseLong((String.valueOf(objects[2]))));        	
        	clsAuthStatDD.setStatYy		(Long.parseLong((String.valueOf(objects[3]))));
        	clsAuthStatDD.setStatMm		(Long.parseLong((String.valueOf(objects[4]))));
        	clsAuthStatDD.setStatDd		(Long.parseLong((String.valueOf(objects[5]))));
        	System.out.println(clsAuthStatDD);
        	clsAuthStatDDRepository.save(clsAuthStatDD);
		}		
		System.out.println("##################### insertAuthStat terminated ");
	}
}
