package action;

import common.BsFactory;
import common.BsPageList;
import domain.BsBook;
import iservice.IBsBookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BsBookAction extends BsBaseAction {

    private static final long serialVersionUID = 1L;
    private final static int PAGE_SIZE = 10;  // 每一页显示的条数
    private IBsBookService bookService = (IBsBookService) BsFactory.getBean("bookService");
    private BsBook book;
    private Integer bookId;
    private BsPageList<BsBook> pageList;  // 分页器
    private Integer pageNo;  // 当前页号


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.processRequest(request, response);
    }

    @Override
    protected void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.manage(request, response);
    }

    @Override
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.add(request, response);
    }

    @Override
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.edit(request, response);
    }

    @Override
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.delete(request, response);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }
}
