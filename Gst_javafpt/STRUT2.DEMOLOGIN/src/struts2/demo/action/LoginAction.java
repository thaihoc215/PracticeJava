/**
 * 
 */
package struts2.demo.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author hochnt
 *
 */
public class LoginAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName = null;
	private String password = null;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String execute() throws Exception {
		if(this.userName != null && this.password != null) {
			return "success";
		}else {
			return "error";
		}
	}
}
