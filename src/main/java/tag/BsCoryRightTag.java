package tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 网站版权标签
 */

public class BsCoryRightTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        try {
            out.print("    <div class=\"footer_copyright\">\n" +
                    "        <span>Copyright (C) 网上书城 2019, All Rights Reserved</span>\n" +
                    "    </div>");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JspFragment fragment = getJspBody();

        if (fragment != null) {
            fragment.invoke(out);
        }
    }
}
