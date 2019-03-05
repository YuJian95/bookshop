package tag;

import common.BsFactory;
import hbm.BsCategory;
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
            stringBuilder.append("<select name=\"catId\" size=\"1\"?");

            for (BsCategory category : list) {
                stringBuilder.append("<option value=\"").append(category.getCatId());
                stringBuilder.append("\">").append(category.getCatName()).append("</a>");
            }

            stringBuilder.append("</select>");
            out.print(stringBuilder.toString());

            JspFragment fragment = getJspBody();

            if (fragment != null) {
                fragment.invoke(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
