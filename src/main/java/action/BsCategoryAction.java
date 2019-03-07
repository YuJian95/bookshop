package action;

import common.BsFactory;
import common.MyException;
import domain.BsCategory;
import iservice.IBsCategoryService;

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


    // 修改分类
    @Override
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BsCategory category = new BsCategory();
        category.setCatId(Integer.parseInt(request.getParameter("catId")));
        category.setCatName(request.getParameter("catName"));
        try {
            categoryService.editCategory(category);
            response.sendRedirect("/bs/BsCategoryAction?method=manage");
        } catch (MyException e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 管理分类
    @Override
    protected void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BsCategory> categoryList = categoryService.findCategories();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/manage.jsp");
        requestDispatcher.forward(request, response);
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

    // 要修改的分类
    @Override
    protected void willEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer catId = Integer.parseInt(request.getParameter("catId"));
        BsCategory category = categoryService.findCategory(catId);
        request.setAttribute("category", category);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/edit.jsp");
        requestDispatcher.forward(request, response);
    }

}
