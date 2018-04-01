package org.hochnt.springmvcweblogin.dao;

import java.util.List;

import org.hochnt.springmvcweblogin.model.UserInfo;

public interface UserInfoDAO {

	public UserInfo findUserInfo(String userName);

	// [USER,AMIN,..]
	public List<String> getUserRoles(String userName);

}
