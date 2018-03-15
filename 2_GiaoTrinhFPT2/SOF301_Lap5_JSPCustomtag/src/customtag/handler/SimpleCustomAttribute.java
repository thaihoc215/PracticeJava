package customtag.handler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SimpleCustomAttribute extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String att1;
	private String att2;
	
	
	@Override
	public int doStartTag() throws JspException {
		//dung phuong thuc de in ra noi dung html cho custom tag
		JspWriter out = this.pageContext.getOut();
		try {
			out.println("<h1>Demo custom tag with Attribute</h1>");
			out.println("<b>Hell Thai Hoc " + att1 + "-" + att2 + "</b>");
		} catch (IOException e) {
			Logger.getLogger(SimpleCustomTag.class.getName())
				.log(Level.SEVERE, null, e);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		// return super.doEndTag();
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		this.att1 = "123";
		this.att2 = "123";
	}
	
	public String getAtt1() {
		return att1;
	}

	public void setAtt1(String att1) {
		this.att1 = att1;
	}

	public String getAtt2() {
		return att2;
	}

	public void setAtt2(String att2) {
		this.att2 = att2;
	}
}
