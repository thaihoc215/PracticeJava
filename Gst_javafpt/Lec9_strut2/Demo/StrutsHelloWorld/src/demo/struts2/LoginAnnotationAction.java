package demo.struts2;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.opensymphony.xwork2.validator.annotations.*;

@Results({
	   @Result(name="success", location="/Welcome.jsp"),
	   @Result(name="error", location="/LoginAnnotation.jsp"),
	   @Result(name="input", location="/LoginAnnotation.jsp")
	})
	
public class LoginAnnotationAction extends ActionSupport {
    private String username;
	private String password;
   
	@Action(value="/loginanotation")
    public String execute() {
        if (this.username.equals("admin")
                && this.password.equals("admin123")) {
            return "success";
        } else {
            addActionError(getText("error.login"));
            return "error";
        }
    }
    @RequiredFieldValidator( message = "The Username is required" )
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
    
    @RequiredFieldValidator( message = "The Password is required" )
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
     
}