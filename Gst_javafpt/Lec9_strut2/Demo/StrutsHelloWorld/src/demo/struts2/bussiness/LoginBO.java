package demo.struts2.bussiness;

import java.io.FileNotFoundException;

import demo.struts2.exception.ABSDException;
import demo.struts2.valueobj.UserVO;

public interface LoginBO {

	public boolean checkAuth(String userName, String password)
			throws ABSDException;
	public boolean checkAuth(UserVO userVO)
	throws ABSDException;
}
