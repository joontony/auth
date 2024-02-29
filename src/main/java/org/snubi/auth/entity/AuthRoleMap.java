/****************************************************************************************
 * File Name    : AuthRoleMap.java
 * Function     :
 * Author       : 최명호
 * Tester       :
 * Page         :
 * Target       :
 * Description  :
 * Modification Log
 * ======================================================================================
 * Ver  Date        Author     Modification
 * ======================================================================================
   1.0  2018.04.06  최명호    Create
****************************************************************************************/
package org.snubi.auth.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@ToString
@Table(name = "tb_auth_role_map")
public class AuthRoleMap extends CommonTrack {

	@Id
	@GeneratedValue
	@Column(nullable = false, name = "seq_auth_role_map",columnDefinition="int(11)")
	private long seqAuthRoleMap;

	@NotNull
	@Column(nullable = false, name = "system_code")
	@Size(min = 1, max = 20)
	private String systemCode;

	@NotNull
	@Column(nullable = false, name = "role_code")
	@Size(min = 1, max = 20)
	private String roleCode;

	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "seq_auth_role_map",insertable = false ,updatable = false)
	List<AuthRoleMapElements> listAuthRoleMapElements;

}
