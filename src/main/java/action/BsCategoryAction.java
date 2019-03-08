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
        try {
            List<BsCategory> categoryList = categoryService.findCategories();
            request.setAttribute("categoryList", categoryList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/bs/category/manage.jsp");
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

    // 要修改的分类
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

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);  // 检查是否是multipart表单

        if (isMultipart) {
            uploadFile(request, response);  // 添加
        } else {
            String method = request.getParameter("method");  // 获取method参数的值，调用对应的方法

            if (method.equals("manage")) {
                manage(request, response);  // 管理
            } else if (method.equals("browse")) {
                browse(request, response);  // 浏览
            } else if (method.equals("show")) {
                show(request, response);  // 显示
            } else if (method.equals("add")) {
                add(request, response);  // 添加
            } else if (method.equals("willEdit")) {
                willEdit(request, response);  // 要修改
            } else if (method.equals("edit")) {
                edit(request, response);  // 修改
            } else if (method.equals("delete")) {
                delete(request, response);  // 删除
            }
        }
    }

}
