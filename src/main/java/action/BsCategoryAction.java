package action;

import common.BsFactory;
import common.BsPageList;
import exception.MyException;
import domain.BsCategory;
import iservice.IBsCategoryService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 分类控制类
 */

public class BsCategoryAction extends BsBaseAction {
    private final static int PAGE_SIZE = 10;
    private IBsCategoryService categoryService = (IBsCategoryService) BsFactory.getBean("categoryService");

    private BsCategory category;
    private Integer catId;
    private BsPageList<BsCategory> pageList;  // 分页器
    private Integer pageNo;

    // 修改分类
    @Override
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        catId = Integer.parseInt(request.getParameter("catId"));
        try {
            category = new BsCategory(catId, request.getParameter("catName"));
            categoryService.editCategory(category);
            response.sendRedirect("/bs/BsCategoryAction?method=manage");

        } catch (MyException e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    //分页显示分类
    @Override
    protected void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (pageNo == null) {
            pageNo = 1;
        }

        int count = categoryService.findCount();

        try {
            List<BsCategory> categoryList = categoryService.findCategories(pageNo, PAGE_SIZE);
            pageList = new BsPageList<BsCategory>(categoryList, count, PAGE_SIZE, pageNo, "/bs/BsCategoryAction?method=manage");
            request.setAttribute("pageList", pageList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/bs/category/manage.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 管理分类
    @Override
    protected void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (pageNo == null) {
            pageNo = 1;
        }

        int count = categoryService.findCount();

        try {
            List<BsCategory> categoryList = categoryService.findCategories(pageNo, PAGE_SIZE);
            pageList = new BsPageList<>(categoryList, count, PAGE_SIZE, pageNo, "/bs/BsCategoryAction?method=manage");
            request.setAttribute("pageList", pageList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/manage.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 添加分类
    @Override
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BsCategory category = new BsCategory();
        category.setCatName(request.getParameter("catName"));

        try {
            categoryService.addCategory(category);
            response.sendRedirect("/bs/BsCategoryAction?method=manage");  // 返回管理页面
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 删除分类
    @Override
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer catId = Integer.parseInt(request.getParameter("catId"));

        try {
            categoryService.deleteCategory(catId);
            response.sendRedirect("/bs/BsCategoryAction?method=manage");
        } catch (MyException e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 将要修改的分类, 转发到edit.jsp
    @Override
    protected void willEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer catId = Integer.parseInt(request.getParameter("catId"));
            BsCategory category = categoryService.findCategory(catId);
            request.setAttribute("category", category);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/edit.jsp");
            requestDispatcher.forward(request, response);

        } catch (MyException e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }


}
