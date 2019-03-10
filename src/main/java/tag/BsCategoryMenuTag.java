package tag;

import common.BsFactory;
import exception.MyException;
import domain.BsCategory;
import iservice.IBsCategoryService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * 分类菜单,点击跳转到指定分类
 */

public class BsCategoryMenuTag extends SimpleTagSupport {
    private IBsCategoryService categoryService = (IBsCategoryService) BsFactory.getBean("categoryService");

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        try {
            List<BsCategory> list = categoryService.findCategories();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<table>\n" +
                    "\t\t<tr>\n" +
                    "\t\t\t<td>\n" +
                    "\t\t\t\t<a href=\"bs/BsBookAction!method=browse&catId=\" target=\"content\">所有</a>\n" +
                    "\t\t\t</td>\n" +
                    "\t\t</tr>\n");

            for (BsCategory category : list) {
                stringBuilder.append("\t\t<tr>\n");
                stringBuilder.append("\t\t\t<td>\n" +
                        "\t\t\t\t<a href=\"bs/BsBookAction!method=browse&catId=");
                stringBuilder.append(category.getCatId());
                stringBuilder.append("\" target=\"content\">" + category.getCatName() + "</a>\n" +
                        "\t\t\t</td>" +
                        "\t\t</tr>\n");
            }

            stringBuilder.append("\t</table>");

            out.print(stringBuilder.toString());
            JspFragment fragment = getJspBody();

            if (fragment != null) {
                fragment.invoke(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("分类菜单标签运行出错");
        }
    }
}
