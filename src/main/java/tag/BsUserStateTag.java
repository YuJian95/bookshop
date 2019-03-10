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
        StringBuilder stringBuilder = new StringBuilder();


        if (user == null) {  // 当登录用户为空时

            stringBuilder.append("                    <li><a href=\"user/add.jsp\"><i class=\"fas fa-registered\"></i></span>注册</a></li>\n");
            stringBuilder.append("                    <li><a href=\"user/login.jsp\"><i class=\"fas fa-sign-in-alt\"></i></span>登录</a></li>");

        } else {  // 当用户已经登录时
            stringBuilder.append("<li class=\"dropdown\">\n" +
                    "                        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">\n");
            stringBuilder.append("                            <i class=\"fas fa-users-cog\"></i></span>用户:");
            stringBuilder.append(user.getUserName());
            stringBuilder.append("<b class=\"caret\"></b>\n" +
                    "                        </a>\n" +
                    "                        <ul class=\"dropdown-menu\">\n" +
                    "                            <li><a href=\"/bs/user/edit.jsp\"><i class=\"fas fa-user-edit\"></i>修改信息</a></li>\n" +
                    "                            <li><a href=\"/bs/user/logout.jsp\"><i class=\"fas fa-sign-out-alt\"></i>退出登录</a></li>\n" +
                    "                        </ul>\n");
            stringBuilder.append("                    </li>");
        }

        try {
            this.pageContext.getOut().print(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.doStartTag();
    }
}
