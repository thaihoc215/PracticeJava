package demo.struts2.dao;

import java.io.File;

import demo.struts2.exception.DataAccessException;
import demo.struts2.exception.FunctionalException;
import demo.struts2.valueobj.UserVO;

public class LoginDAOImpl implements LoginDAO {
	public boolean checkUserPass(String user, String pass)
			throws DataAccessException, FunctionalException {

		if (user.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin123")) {
			File a = new File("C:/file.txt");
			if (a.exists()) {
				System.out.println(a.getName());
			} else {
				
				throw new FunctionalException("001");
			}
			return true;
		} else {
			throw new DataAccessException("Invalid username & password");
		}

	}

	
	public boolean checkUserPass(UserVO userVO)
	throws DataAccessException, FunctionalException {

if (userVO.getUserName().equalsIgnoreCase("admin") && userVO.getPassword().equalsIgnoreCase("admin123")) {
	File a = new File("C:/file.txt");
	if (a.exists()) {
		System.out.println(a.getName());
	} else {
		
		throw new FunctionalException("001");
	}
	return true;
} else {
	throw new DataAccessException("Invalid username & password");
}

}
}
