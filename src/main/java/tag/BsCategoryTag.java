package tag;

import common.BsFactory;
import domain.BsCategory;
import exception.MyException;
import iservice.IBsCategoryService;


import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.List;

/**
 * 分类选项，以下拉选择框显示
 */

public class BsCategoryTag extends SimpleTagSupport {

    private IBsCategoryService categoryService = (IBsCategoryService) BsFactory.getBean("categoryService");

    @Override
    public void doTag() {
        JspWriter out = getJspContext().getOut();
        try {
            List<BsCategory> list = categoryService.findCategories();
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("<label for=\"name\">选择列表</label>\n" +
                    "    <select name=\"catId\" class=\"form-control\">\n");

            for (BsCategory category : list) {
                stringBuilder.append("      <option value=\"" + category.getCatId() + "\">"
                        + category.getCatName() + "</option>\n");
            }
            stringBuilder.append("    </select>");

            out.print(stringBuilder.toString());
            JspFragment fragment = getJspBody();

            if (fragment != null) {
                fragment.invoke(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("分类下拉框标签出错");
        }
    }
}
