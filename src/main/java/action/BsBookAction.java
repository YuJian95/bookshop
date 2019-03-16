package action;

import com.jspsmart.upload.SmartUpload;
import common.BsFactory;
import common.BsPageList;
import dao.BsBookDao;
import domain.BsBook;
import domain.BsCategory;
import iservice.IBsBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 书籍控制类
 */

public class BsBookAction extends BsBaseAction {
    private static final long serialVersionUID = 1L;
    private static final String PICTURE_FILE_DIR = "D:/bookshop/src/main/webapp/bookimg/"; // 图书图片上传路径
    private final static int PAGE_SIZE = 6;  // 每一页显示的条数

    private IBsBookService bookService = (IBsBookService) BsFactory.getBean("bookService");
    private BsBook book;
    private Integer bookId;
    private Integer catId;
    private BsPageList<BsBook> pageList;  // 分页器
    private Integer pageNo;  // 当前页号
    private String msg;


    // 获取图书信息
    private BsBook getBookInfo(HttpServletRequest request) {

        book = new BsBook();
        try {

            if (request.getParameter("bookId") != null) {
                bookId = Integer.parseInt(request.getParameter("bookId"));
            }

            if (request.getParameter("catId") != null) {
                catId = Integer.parseInt(request.getParameter("catId"));
                System.out.println(catId);
                BsCategory category = new BsCategory();
                category.setCatId(catId);
                book.setCategory(category);
            }

            book.setBookIsbn(request.getParameter("bookIsbn"));
            book.setBookDesc(request.getParameter("bookDesc"));
            book.setBookNum(Integer.parseInt(request.getParameter("bookNum")));
            book.setBookName(request.getParameter("bookName"));
            book.setBookAuthor(request.getParameter("bookAuthor"));
            book.setBookPublisher(request.getParameter("bookPublisher"));
            System.out.println(book.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    // 图书管理
    @Override
    protected void manage(HttpServletRequest request, HttpServletResponse response) {
        if (pageNo == null) {
            pageNo = 1;
        } else {
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        }

        msg = "图书管理:";
        int count;
        List<BsBook> list;
        try {

            count = bookService.findAllCount();
            list = bookService.findBooks();

            pageList = new BsPageList<>(list, count, PAGE_SIZE, pageNo, "/bs/BsBookAction?method=manage");
            request.setAttribute("pageList", pageList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/book/manage.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    // 图书分页,分类浏览,搜索
    @Override
    protected void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "图书分类浏览:";
        List<BsBook> list;
        int count;
        try {

            if (pageNo == null) {
                pageNo = 1;
            } else {
                pageNo = Integer.parseInt(request.getParameter("pageNo"));
            }

            catId = Integer.valueOf(request.getParameter("catId"));

            // 查找图书
            count = bookService.findSomeCount(catId);
            list = bookService.findSomeById(catId);
            System.out.println(list.size());

            pageList = new BsPageList<>(list, count, PAGE_SIZE, pageNo, "/bs/BsBookAction?method=browse");
            System.out.println(pageList.toString());

            request.setAttribute("pageList", pageList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/book/browse.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 图书添加,含上传图片
    @Override
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "图书添加:";
        SmartUpload smartUpload = new SmartUpload();
        ServletConfig config = this.getServletConfig();
        String pictureFileName;
        try {
            smartUpload.initialize(config, request, response);
            smartUpload.upload();

            // 通过smartUpload 获取多功能表单中的数据
            book = new BsBook();
            book.setBookName(smartUpload.getRequest().getParameter("bookName"));
            book.setBookPublisher(smartUpload.getRequest().getParameter("bookPublisher"));
            book.setBookPrice(Double.parseDouble(smartUpload.getRequest().getParameter("bookPrice")));
            book.setBookAuthor(smartUpload.getRequest().getParameter("bookAuthor"));
            book.setBookIsbn(smartUpload.getRequest().getParameter("bookIsbn"));
            book.setBookDesc(smartUpload.getRequest().getParameter("bookDesc"));
            book.setBookNum(Integer.parseInt(smartUpload.getRequest().getParameter("bookNum")));

            // 获取书籍分类
            BsCategory category = new BsCategory();
            category.setCatId(Integer.parseInt(smartUpload.getRequest().getParameter("catId")));
            book.setCategory(category);

            // 上传图片文件并获取图片文件名
            com.jspsmart.upload.File smartFile = smartUpload.getFiles().getFile(0);  // 获取上传的第一文件
            String fileExt = smartFile.getFileExt();
            pictureFileName = new java.util.Date().getTime() + "." + fileExt;
            smartFile.saveAs(PICTURE_FILE_DIR + pictureFileName, SmartUpload.SAVE_AUTO);
            book.setBookPicture(pictureFileName);

            bookService.addBook(book);
            request.setAttribute("msg", msg + "成功" + "<a href =\"/bs/BsBookAction?method=manage\" target=\"top\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 图书修改
    @Override
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "图书修改:";
        try {
            book = getBookInfo(request);
            bookService.editBook(book);

            request.setAttribute("msg", msg + "成功" + "<a href =\"/bs/BsBookAction?method=manage\" target=\"top\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 图书删除
    @Override
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "图书删除:";
        try {
            bookId = Integer.parseInt(request.getParameter("bookId"));
            bookService.deleteBook(bookId);

            request.setAttribute("msg", msg + "成功" + "<a href =\"/bs/BsBookAction?method=manage\" target=\"top\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    // 图书编辑
    @Override
    protected void willEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "编辑图书:";
        try {
            bookId = Integer.parseInt(request.getParameter("bookId"));
            book = bookService.findBookById(bookId);
            request.setAttribute("book", book);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/book/edit.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    // 查看图书
    @Override
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        msg = "查看图书:";
        try {
            bookId = Integer.parseInt(request.getParameter("bookId"));
            book = bookService.findBookById(bookId);
            request.setAttribute("book", book);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/book/show.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    @Override
    protected void change(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "首页展示删除:";
        try {
            bookId = Integer.parseInt(request.getParameter("bookId"));

            Integer isCarousel = new BsBookDao().selectById(bookId).getCarousel();
            if (isCarousel == 1) {
                bookService.editBook(bookId, 0);
            }
            request.setAttribute("msg", msg + "成功" + "<a href =\"/book/manage.jsp\" target=\"top\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }


}
