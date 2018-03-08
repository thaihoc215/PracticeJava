package demo.struts2;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class LocaleAction extends ActionSupport {
   
    public String execute() 
    {
            return SUCCESS;
    }
    
}