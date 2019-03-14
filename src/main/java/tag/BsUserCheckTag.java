package tag;

import domain.BsUser;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * 用户检测
 */

public class BsUserCheckTag extends TagSupport {
    private Integer right;

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    @Override
    public int doEndTag() throws JspException {

        HttpSession session = this.pageContext.getSession();
        BsUser user = (BsUser) session.getAttribute("user");

        try {
            if (user != null && user.getUserRight() <= right) {
                return TagSupport.EVAL_PAGE;
            }

            this.pageContext.getOut().print("请先登录或权限不够,不能访问!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TagSupport.SKIP_PAGE;  //显示标签体的内容
    }


}
