package strut1.demo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import strut1.demo.form.LoginForm;

public class LoginAction extends Action{
//dong vai tro nhu mot controller
	@Override
	//phuong thuc macdinh de xu ly lop log action
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginForm loginForm = (LoginForm) form;
		if(loginForm.getUserName() == null || loginForm.getPassword() == null
				|| !loginForm.getUserName().equalsIgnoreCase("helios")
				|| !loginForm.getPassword().equals("helios")) {
			return mapping.findForward("failure");
		}else {
			request.getSession().setAttribute("name", loginForm.getUserName());
			//dieu huong trang tra ve voi action tuong ung
			return mapping.findForward("success");
		}
	}
}
