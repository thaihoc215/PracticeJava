package customtag.handler;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class SimpleCustomTag extends TagSupport{
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
//		return super.doEndTag();
		return EVAL_PAGE;
	}
}
