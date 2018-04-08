package org.hochnt.springmvcsocialjdbc.security;

import org.hochnt.springmvcsocialjdbc.model.MyUserAccount;
import org.hochnt.springmvcsocialjdbc.user.MySocialUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

	// Tự động đăng nhập.
	public static void logInUser(MyUserAccount user) {

		MySocialUserDetails userDetails = new MySocialUserDetails(user);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
