package demo.struts2;

import java.util.HashMap;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import demo.bean.Profile;

import org.apache.tiles.impl.BasicTilesContainer;

public class ProfileAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Profile profile;
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String execute() {

		   return "input";
	
	}

	public String doView() {

		
		if(this.getProfile().getFullname() != null){
				ValueStack stack = ActionContext.getContext().getValueStack();
				Map<String, Object> context = new HashMap<String, Object>();
				context.put("fullname", profile.getFullname());
				context.put("age", profile.getAge());
				context.put("address", profile.getAddress());
				context.put("description", profile.getDescription());
				stack.push(context);
				return "view";
		}else
		   return "input";
	
	}
	
}