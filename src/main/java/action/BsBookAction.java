package action;

import common.BsFactory;
import common.BsPageList;
import domain.BsBook;
import domain.BsCategory;
import iservice.IBsBookService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * 书籍控制类
 */

public class BsBookAction extends BsBaseAction {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "bookimg";  // 上传图片的存储路径
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;  // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;  // 50MB
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
    private String msg;


    // 获取图书信息
    private BsBook getBookInfo(HttpServletRequest request) {

        book = new BsBook();

        if (request.getParameter("bookId") != null) {
            bookId = Integer.parseInt(request.getParameter("bookId"));
        }

        catId = Integer.parseInt(request.getParameter("catId"));
        BsCategory category = new BsCategory();
        category.setCatId(catId);

        book.setCategory(category);
        book.setBookIsbn(request.getParameter("bookIsbn"));
        book.setBookDesc(request.getParameter("bookDesc"));
        book.setBookNum(Integer.parseInt(request.getParameter("bookNum")));
        book.setBookName(request.getParameter("bookName"));
        book.setBookAuthor(request.getParameter("bookAuthor"));
        book.setBookPrice(Double.parseDouble(request.getParameter("bookPrice")));
        book.setBookPublisher(request.getParameter("bookPublisher"));


//        book.setBookPicture(reques);
        bookService.addBook(book);

        return book;
    }

    // 设置页面初始参数
    private void setInfo(HttpServletRequest request) throws ServletException, IOException {

        if (pageNo == null) {
            pageNo = 1;
        } else {
            catId = Integer.parseInt(request.getParameter("catId"));
            bookName = request.getParameter("bookName");
            bookAuthor = request.getParameter("bookAuthor");
        }
    }

    // 图书管理
    @Override
    protected void manage(HttpServletRequest request, HttpServletResponse response) {
        msg = "图书管理:";
        int count;
        List<BsBook> list;
        try {
            setInfo(request);

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
            setInfo(request);
            catId = Integer.valueOf(request.getParameter("catId"));

            if (bookAuthor == null || bookName == null) {
                list = bookService.findSomeById(catId);
                count = bookService.findSomeCount(catId);
            } else {// 查找图书
                count = bookService.findCount(catId, bookName, bookAuthor);
                list = bookService.findBooks(catId, bookName, bookAuthor, pageNo, PAGE_SIZE);
            }

            pageList = new BsPageList<>(list, count, PAGE_SIZE, pageNo, "/bs/BsBookAction?method=browse");
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
        System.out.println("正在添加");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);  // 内存临界值
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));  // 创建临时存储目录

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);  // 最大请求值
        upload.setHeaderEncoding("UTF-8");
        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            book = getBookInfo(request);

            List<FileItem> fileItems = upload.parseRequest(request);
            if (fileItems != null && fileItems.size() > 0) {
                for (FileItem fileItem : fileItems) {
                    if (!fileItem.isFormField()) {
                        String fileName = new File(new java.util.Date() + ".jpg").getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        fileItem.write(storeFile);
                        book.setBookPicture(filePath);
                    }
                }
            }
            bookService.addBook(book);

            request.setAttribute("msg", msg + "成功" + "<a href =\"/book/manage.jsp\" target=\"top\">返回</a>");
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

            request.setAttribute("msg", msg + "成功" + "<a href =\"/book/manage.jsp\" target=\"top\">返回</a>");
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

            request.setAttribute("msg", msg + "成功" + "<a href =\"/book/manage.jsp\" target=\"top\">返回</a>");
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

    // 上传文件
    @Override
    protected void uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.uploadFile(request, response);
    }
}
