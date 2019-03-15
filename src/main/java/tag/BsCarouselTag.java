package tag;

import common.BsFactory;
import domain.BsBook;
import exception.MyException;
import iservice.IBsBookService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class BsCarouselTag extends SimpleTagSupport {

    private IBsBookService bookService = (IBsBookService) BsFactory.getBean("bookService");

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        List<BsBook> list = bookService.findCarouselBooks();
        BsBook book;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<div id=\"myCarousel\" class=\"carousel slide\">\n" +
                    "                <ol class=\"carousel-indicators\">\n");
            for (int i = 0; i < list.size(); i++) {
                book = list.get(i);
                stringBuilder.append("\t\t\t        <li data-target=\"#myCarousel\" data-slide-to=\"" + i);
                if (i == 0) {
                    stringBuilder.append(" class=\"active\"");
                }
                stringBuilder.append("\"></li>\n");
            }
            stringBuilder.append("\t\t\t    </ol>   \n" +
                    "\t\t\t    <div class=\"carousel-inner\">\n");

            for (int i = 0; i < list.size(); i++) {
                book = list.get(i);
                if (i == 0) {
                    stringBuilder.append("<div class=\"item active\">");
                } else {
                    stringBuilder.append("\t\t\t        <div class=\"item\">\n");
                }
                stringBuilder.append(
                        "\t\t\t        \t<a href=\"/bs/BsBookAction?method=show&bookId=" + book.getBookId() + "\">\n" +
                                "\t\t\t            \t<img src=\"bookimg/" + book.getBookPicture() + "\" height=\"450px\" width=\"300px\" alt=\"" + book.getBookName() + "\">\n" +
                                "\t\t\t            </a>\n" +
                                "\t\t\t        </div>\n");
            }

            stringBuilder.append("</div>\n" +
                    "                <a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\">\n" +
                    "                    <span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>\n" +
                    "                    <span class=\"sr-only\">Previous</span>\n" +
                    "                </a>\n" +
                    "                <a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\">\n" +
                    "                    <span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>\n" +
                    "                    <span class=\"sr-only\">Next</span>\n" +
                    "                </a>\n" +
                    "            </div>");
            out.print(stringBuilder.toString());
            JspFragment fragment = getJspBody();

            if (fragment != null) {
                fragment.invoke(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("轮播标签显示失败");
        }
    }
}
