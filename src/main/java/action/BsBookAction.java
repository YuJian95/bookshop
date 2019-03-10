package action;

import common.BsFactory;
import common.BsPageList;
import domain.BsBook;
import exception.MyException;
import iservice.IBsBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 书籍控制类
 */

public class BsBookAction extends BsBaseAction {

    private static final long serialVersionUID = 1L;
    private final static int PAGE_SIZE = 6;  // 每一页显示的条数
    private IBsBookService bookService = (IBsBookService) BsFactory.getBean("bookService");
    private BsBook book;
    private Integer bookId;
    private Integer catId;
    private String bookName;
    private String bookAuthor;
    private File uploadFile;
    private BsPageList<BsBook> pageList;  // 分页器
    private Integer pageNo;  // 当前页号


    // 图书管理
    @Override
    protected void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.manage(request, response);
    }

    @Override
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            bookService.addBook(getBookInfo(request));
            response.sendRedirect("/bs/book/manage.jsp");
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            bookService.editBook(getBookInfo(request));
            response.sendRedirect("/bs/book/manage.jsp");
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            bookService.deleteBook(Integer.parseInt(request.getParameter("bookId")));
            response.sendRedirect("/Book/BookAction?method=manage");
        } catch (MyException e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.browse(request, response);
    }

    @Override
    protected void willEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.willEdit(request, response);
    }

    @Override
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.show(request, response);
    }

    @Override
    protected void uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.uploadFile(request, response);
    }


    private BsBook getBookInfo(HttpServletRequest request) {

        book = new BsBook();
        book.setBookIsbn(request.getParameter("bookIsbn"));
        book.setBookDesc(request.getParameter("bookDesc"));
        book.setBookNum(Integer.parseInt(request.getParameter("bookNum")));
        book.setBookName(request.getParameter("bookName"));
        book.setBookAuthor(request.getParameter("bookAuthor"));
        book.setBookPrice(Double.parseDouble(request.getParameter("bookPrice")));
        book.setBookPublisher(request.getParameter("bookPublisher"));
        //book.setCategory();
//        book.setBookPicture(reques);
        bookService.addBook(book);

        return book;
    }
}
