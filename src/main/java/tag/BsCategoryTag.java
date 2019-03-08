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
 * 产品分类,供添加图书时选择类别
 */

public class BsCategoryTag extends SimpleTagSupport {
    private IBsCategoryService categoryService = (IBsCategoryService) BsFactory.getBean("categoryService");

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        try {
            List<BsCategory> list = categoryService.findCategories();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<ul class=\"nav nav-pills nav-stacked text-center\">\n");

            for (BsCategory category : list) {
                stringBuilder.append(
                        "                <li>" + category.getCatName() + "</li>\n");
            }

            stringBuilder.append("            </ul>");
            out.print(stringBuilder.toString());

            JspFragment fragment = getJspBody();

            if (fragment != null) {
                fragment.invoke(out);
            }
        } catch (IOException e) {
            throw new MyException("分类标签运行出错");
        }
    }
}
