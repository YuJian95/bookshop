package tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class BsCoryrightTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        try {
            out.print("<p class=\"text-center\">Copyright <span class=\"glyphicon glyphicon-copyright-mark\">" +
                    "</span> 2019 xx.com. All Rights Reserved"
                    + "</p>");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JspFragment fragment = getJspBody();

        if (fragment != null) {
            fragment.invoke(out);
        }
    }
}
