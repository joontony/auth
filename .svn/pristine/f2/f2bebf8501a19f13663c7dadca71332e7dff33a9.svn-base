/****************************************************************************************
 * File Name    : CustomUserDetailsServiceImplement.java
 * Function     :
 * Author       : mh.choi
 * Tester       :
 * Page         :
 * Target       :
 * Description  :
 * Modification Log
 * ======================================================================================
 * Ver  Date        Author     	Modification
 * ======================================================================================
   1.0  2018.04.06  mh.choi    	Create
****************************************************************************************/
package org.snubi.auth.security.service;

import org.snubi.auth.member.repository.AuthMemberSetRepository;
import org.snubi.auth.security.AuthSecurityMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImplement implements UserDetailsService {

	@Autowired
	AuthMemberSetRepository clsAuthMemberSetRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//		return Optional.ofNullable(clsAuthMemberSetRepository.findOne(userId)).filter(m -> m!= null).map(m -> new AuthSecurityMember(m)).get();
		return new AuthSecurityMember(clsAuthMemberSetRepository.findOne(userId));
	}
}
