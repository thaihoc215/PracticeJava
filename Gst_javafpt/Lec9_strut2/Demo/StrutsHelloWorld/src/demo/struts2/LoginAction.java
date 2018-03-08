package demo.struts2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import demo.bean.Lion;
import demo.struts2.bussiness.LoginBO;
import demo.struts2.bussiness.LoginBOImpl;
import demo.struts2.exception.ABSDException;
import demo.struts2.valueobj.UserVO;

import org.apache.tiles.impl.BasicTilesContainer;

public class LoginAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	private String[] myList1 = new String[] { "One", "Two", "Three" };
	private String[] myList2 = new String[] { "Four", "Five", "Six" };
	private String[] myList3 = new String[] { "Seven", "Eight", "Nine" };
	private List<Lion> listLion = new ArrayList();
	private Lion lion = new Lion();

	public Lion getLion() {
		return lion;
	}

	public void setLion(Lion lion) {
		this.lion = lion;
	}

	public List<Lion> getListLion() {
		return listLion;
	}

	public void setListLion(List<Lion> listLion) {
		this.listLion = listLion;
	}

	private boolean status = true;
	private Person person = new Person();

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String[] getMyList1() {
		return myList1;
	}

	public void setMyList1(String[] myList1) {
		this.myList1 = myList1;
	}

	public String[] getMyList2() {
		return myList2;
	}

	public void setMyList2(String[] myList2) {
		this.myList2 = myList2;
	}

	public String[] getMyList3() {
		return myList3;
	}

	public void setMyList3(String[] myList3) {
		this.myList3 = myList3;
	}

	public String execute() {

		System.out.println("Code in processing...::" + this.username + ":"
				+ this.password);
		LoginBO loginBO = new LoginBOImpl();

		if (this.username != null && this.password != null) {
			if (true) {

				try {
					UserVO userVO = new UserVO();
					userVO.setPassword(password);
					userVO.setUserName(username);
					//loginBO.checkAuth(username, password);
					loginBO.checkAuth(userVO);
				} catch (ABSDException ex) {

					addFieldError("username", ex.getMessage());
					return "error";
				}

				ValueStack stack = ActionContext.getContext().getValueStack();
				Map<String, Object> context = new HashMap<String, Object>();
				context.put("key1", new String("This is key1"));
				context.put("key2", new String("This is key2"));

				stack.push(context);

				person.setName("Nguyen Van A");
				person.setAddress("Hanoi, VN");

				System.out.println(this.lion.getLionType());

				Lion lion = null;
				for (int i = 1; i <= 5; i++) {
					lion = new Lion();
					lion.setLionType("Type " + i);
					lion.setLionName("Name " + i);
					listLion.add(lion);
				}

				// return "success";
				return this.SUCCESS;

			} else {
				addActionError(getText("error.login"));
				return "error";

			}
		} else {
			Lion lion = null;
			for (int i = 1; i <= 5; i++) {
				lion = new Lion();
				lion.setLionType("Type " + i);
				lion.setLionName("Name " + i);
				listLion.add(lion);
			}
			return "input";
		}
	}

	@Override
	public void validate() {
		System.out.println("Login::: validate()");
		if (username == null || username.trim().equals("")) {
			addFieldError("username", "The name is required");
		}
		if (password == null || password.trim().equals("")) {
			addFieldError("password", "The password is required");
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}