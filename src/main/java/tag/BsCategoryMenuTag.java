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
            stringBuilder.append("            <ul class=\"nav nav-pills nav-stacked text-center\">\n");
            for (BsCategory category : list) {
                stringBuilder.append("                <li>");
                stringBuilder.append("<a href=\"/bs/BsBookAction?method=browse&catId=");
                stringBuilder.append(category.getCatId());
                stringBuilder.append("\">" + category.getCatName() + "</a></li>\n");
            }
            stringBuilder.append("            </ul>\n");

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
