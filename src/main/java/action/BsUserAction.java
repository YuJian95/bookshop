package action;

import common.BsFactory;
import common.MyException;
import domain.BsUser;
import iservice.IBsUserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户控制类
 */

public class BsUserAction extends BsBaseAction {

    private static final long serialVersionUID = 1L;
    private final static int PAGE_SIZE = 10;
    private int ADMIN_RIGHT = 2;
    private IBsUserService userService = (IBsUserService) BsFactory.getBean("userService");

    // 添加新用户
    @Override
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            userService.addUser(getUserInfo(request));
            response.sendRedirect("/index.jsp");  // 返回主页
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 修改用户信息
    @Override
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getUserInfo(request);
        BsUser user = getUserInfo(request);

        try {
            userService.editUser(user);
            response.sendRedirect("/index.jsp");  // 返回主页
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 查看所有用户信息
    @Override
    protected void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.browse(request, response);
    }

    // 查看用户
    @Override
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BsUser user = userService.findUserById(Integer.parseInt(request.getParameter("userId")));
        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/show.jsp");
        requestDispatcher.forward(request, response);
    }

    // 删除用户
    @Override
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.parseInt(request.getParameter("userId"));

        try {
            userService.deleteUser(userId);
            response.sendRedirect("/User/BsUserAction?method=manage");
        } catch (MyException e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    // 登录用户
    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        BsUser user = userService.findUserByName(userName, password);  // 查找用户
        HttpSession session = request.getSession();

        if (user != null) {
            session.setAttribute("user", user);

            if (user.getUserRight() >= ADMIN_RIGHT) {  // 管理员权限
                response.sendRedirect("/admin/index.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        }

    }

    // 获取用户信息
    private BsUser getUserInfo(HttpServletRequest request) {

        BsUser user = new BsUser();
        user.setUserName(request.getParameter("userName"));
        user.setUserPwd(request.getParameter("password"));
        user.setUserRealName(request.getParameter("realName"));
        user.setUserPhone(request.getParameter("phone"));
        user.setUserEmail(request.getParameter("email"));
        user.setUserAddr(request.getParameter("address"));

        return user;
    }


}
