package org.hochnt.springmvcsocialjdbc.service;

import org.hochnt.springmvcsocialjdbc.dao.MyUserAccountDAO;
import org.hochnt.springmvcsocialjdbc.model.MyUserAccount;
import org.hochnt.springmvcsocialjdbc.user.MySocialUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;

//Lấy thông tin User dưới database.
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MyUserAccountDAO myUserAccountDAO;

	public MyUserDetailsService() {

	}

	// (Phương thức này được sử dụng bởi Spring Security API).
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		MyUserAccount myUserAccount = myUserAccountDAO.findByUserName(userName);

		if (myUserAccount == null) {
			throw new UsernameNotFoundException("No user found with userName: " + userName);
		}

		// Chú ý: SocialUserDetails mở rộng từ interface UserDetails.
		UserDetails principal =  User.withDefaultPasswordEncoder().username(myUserAccount.getUserName()).
		password(myUserAccount.getPassword()).roles(myUserAccount.getRole()).build();
//		SocialUserDetails principal = new MySocialUserDetails(myUserAccount);

		return principal;
	}

}