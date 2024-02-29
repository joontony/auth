/****************************************************************************************
 * File Name    : CommonTrack.java
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

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class CommonTrack implements Serializable {

	private static final long serialVersionUID = -1909322766766026089L;

	@NotNull
	@Column(nullable = false, name = "valid")
	protected String valid;

	@NotNull
	@Column(nullable = false, name = "creator")
	protected String creator;

	@NotNull
	@Column(nullable = false, name = "created", columnDefinition="datetime")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Seoul")
	protected Date created;

	@NotNull
	@Column(nullable = false, name = "updater")
	protected String updater;

	@NotNull
	@Column(nullable = false, name = "updated", columnDefinition="datetime")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date updated;
}
