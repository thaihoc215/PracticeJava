package customtag.handler;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class bai1_MyTagHandler extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5533968986457853431L;

	@Override
	public int doStartTag() throws JspException {
		//dung phuong thuc de in ra noi dung html cho custom tag
		JspWriter out = this.pageContext.getOut();
		try {
			out.println(Calendar.getInstance().getTime());
		} catch (IOException e) {
			Logger.getLogger(SimpleCustomTag.class.getName())
				.log(Level.SEVERE, null, e);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
