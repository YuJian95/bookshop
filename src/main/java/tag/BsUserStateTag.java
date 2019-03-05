package tag;


import domain.BsUser;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * 用户状态标签
 */

public class BsUserStateTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = this.pageContext.getSession();
        BsUser user = (BsUser) session.getAttribute("user");
        StringBuffer stringBuffer = new StringBuffer();
        if (user == null) {
            stringBuffer.append("<form method=\"POST\" action=\"/bs/BsUserAction!login.action\">");
            stringBuffer.append("<p>用户 <input type=\"text\" name=\"user.userName\" style=\"width:120px;height:20px\"/></p>");
            stringBuffer.append("<p>密码 <input type=\"password\" name=\"user.userPwd\" style=\"width:120px;height:20px\"/></p>");
            stringBuffer.append("<p><input type=\"submit\" value=\"登录\"/>&nbsp;" +
                    "<input type=\"button\" value=\"注册\" " +
                    "onclick=\"content.location.href=\'/bs/user/register.jsp\'\"/></p>");
            stringBuffer.append("</p>");
            stringBuffer.append("</form>");
        } else {
            stringBuffer.append("<br/><p>当前用户:").append(user.getUserName()).append("</p>");
            stringBuffer.append("<p><input type=\"button\" value=\"注销\" onclick=\"top.location.href='/bs/user/logout.jsp'\"/>&nbsp;<input type=\"button\" value=\"个人修改\"" +
                    "onclick=\"content.location.href='/bs/user/edit.jsp'\"/></p>");
        }

        try {
            this.pageContext.getOut().print(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.doStartTag();
    }
}
