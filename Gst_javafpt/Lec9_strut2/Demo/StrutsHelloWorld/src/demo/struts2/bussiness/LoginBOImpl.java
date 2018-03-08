package demo.struts2.bussiness;

import java.io.FileNotFoundException;

import javax.jws.soap.SOAPBinding.Use;

import demo.struts2.dao.LoginDAO;
import demo.struts2.dao.LoginDAOImpl;
import demo.struts2.exception.ABSDException;
import demo.struts2.valueobj.UserVO;

public class LoginBOImpl implements LoginBO{

	public boolean checkAuth(String userName, String password)
throws ABSDException{
		LoginDAO loginDAO = new LoginDAOImpl();
		return loginDAO.checkUserPass(userName, password);
	}
	
	public boolean checkAuth(UserVO useVO)
	throws ABSDException{
			LoginDAO loginDAO = new LoginDAOImpl();
			return loginDAO.checkUserPass(useVO);
		}
}
