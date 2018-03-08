package demo.struts2.dao;
import demo.struts2.exception.DataAccessException;
import demo.struts2.exception.FunctionalException;
import demo.struts2.valueobj.UserVO;

public interface LoginDAO {

public 	boolean checkUserPass(String user, String pass) throws DataAccessException, FunctionalException; 

public 	boolean checkUserPass(UserVO userVO) throws DataAccessException, FunctionalException; 

}

