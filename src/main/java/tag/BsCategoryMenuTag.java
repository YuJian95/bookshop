package tag;

import common.BsFactory;
import domain.BsCategory;
import iservice.IBsCategoryService;


import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.List;

/**
 * 显示产品分类,首页使用
 */

public class BsCategoryMenuTag extends SimpleTagSupport {

    private IBsCategoryService categoryService = (IBsCategoryService) BsFactory.getBean("categoryService");

    @Override
    public void doTag() {
        JspWriter out = getJspContext().getOut();
        try {
            List<BsCategory> list = categoryService.findCategories();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<table cellspacing=\"0\" cellpadding=\"0\" style=\"border-width:0px;height:100%\">");
            stringBuilder.append("<tr>");
            stringBuilder.append("<td><a href=\"/bs/BsBookAction!browse.action?catId=");
            stringBuilder.append("\" target=\"content\">所有</a></td>");

            for (BsCategory category : list) {
                stringBuilder.append("<td><a href=\"/bs/BsBookAction!browse.action?catId=");
                stringBuilder.append(category.getCatId()).append("\" target=\"content\">");
                stringBuilder.append(category.getCatName()).append("</a></td>");
            }
            stringBuilder.append("</tr>");
            stringBuilder.append("</table>");
            out.print(stringBuilder.toString());
            JspFragment fragment = getJspBody();

            if (fragment != null) {
                fragment.invoke(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
