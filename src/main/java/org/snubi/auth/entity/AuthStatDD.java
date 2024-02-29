/**************************************************************************************** 
 * File Name    : AuthStatD.java
 * Function     : 
 * Author       : mh.choi
 * Tester       : 
 * Page         : 
 * Target       : 
 * Description  : tb_auth_stat_dd Entity
 * Modification Log
 * ====================================================================================== 
 * Ver  Date        Author     	Modification
 * ======================================================================================
   1.0  2019.04.30	최명호		파일생성	
****************************************************************************************/
package org.snubi.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.snubi.lib.date.DateUtil;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "tb_auth_stat_dd")
public class AuthStatDD {
	
	@Id
	@GeneratedValue
	@Column(nullable = false, name = "seq",columnDefinition="int(11)")
	private long seq;
	
	@NotNull
	@Column(nullable = false, name = "gender")
	@Size(min = 0, max = 20)
	private String gender;
	
	@NotNull
	@Column(nullable = false, name = "age")
	private long age;
	
	@NotNull
	@Column(nullable = false, name = "category")
	@Size(min = 0, max = 20)
	private String category;
	
	@NotNull
	@Column(nullable = false, name = "total")
	private long total;
	
	@NotNull
	@Column(nullable = false, name = "stat_yy")
	private long statYy;
	
	@NotNull
	@Column(nullable = false, name = "stat_mm")
	private long statMm;
	
	@NotNull
	@Column(nullable = false, name = "stat_dd")
	private long statDd;
	
	@Column(nullable = false, name = "created", columnDefinition="datetime")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date dateLog;	
	
	@Builder(builderMethodName = "builder")
	public AuthStatDD(String gender,long age,String category,long total,long statYy,long statMm,long statDd) {
		this.gender 	= gender	;
		this.age 		= age		;
		this.category 	= category	;
		this.total 		= total		;
		this.statYy 	= statYy	;
		this.statMm 	= statMm	;
		this.statDd 	= statDd	;
	}
	@PrePersist
    public void prePersist() {
        this.dateLog = DateUtil.getThisDate();
    }
    @PreUpdate
    public void preUpdate() {
    	this.dateLog = DateUtil.getThisDate();
    }
}
