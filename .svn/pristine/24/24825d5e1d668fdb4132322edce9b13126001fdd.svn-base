/****************************************************************************************
 * File Name    : AuthLogRaw.java
 * Function     :
 * Author       : 최명호
 * Tester       :
 * Page         :
 * Target       :
 * Description  : TB_AUTH_LOG_RAW Entity
 * Modification Log
 * ======================================================================================
 * Ver  		Date        Author     	Modification
 * ======================================================================================
   	1.0  		2019.04.30	최명호		파일생성
	1.4.006		2021.12.17	최명호 		스키마추가
****************************************************************************************/
package org.snubi.auth.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.snubi.lib.date.DateUtil;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
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
@Table(name = "tb_auth_log_raw")
@Builder
@AllArgsConstructor
public class AuthLogRaw {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, name = "seq")
	private long seq;

	@NotNull
	@Column(nullable = false, name = "id")
	private String id;

	@NotNull
	@Column(nullable = false, name = "request")
	private String request;

	@Column(nullable = false, name = "method")
	private String method;

	@Column(nullable = false, name = "user_agent")
	private String userAgent;

	@Column(nullable = false, name = "ip")
	private String ip;

	@Column(nullable = false, name = "target")
	private String target;

	@Column(nullable = false, name = "category")
	private String category;

	@Column(nullable = false, name = "category_sub")
	private String categorySub;

	@Column(nullable = false, name = "job")
	private String job;

	@Column(nullable = false, name = "etc")
	private String etc;

	@NotNull
	@Column(nullable = false, name = "date_log")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateLog;

	@PrePersist
    public void prePersist() {
        this.dateLog = DateUtil.getThisDate();
    }
    @PreUpdate
    public void preUpdate() {

    }
}
