package customtag.handler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SimpleCustomTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		//dung phuong thuc de in ra noi dung html cho custom tag
		JspWriter out = this.pageContext.getOut();
		try {
			out.println("<h1>Demo custom tag</h1>");
			out.println("<b>Hell Thai Hoc</b>");
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
}
