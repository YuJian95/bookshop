package action;

import common.BsFactory;
import common.BsPageList;
import dao.BsUserDao;
import exception.MyException;
import domain.BsUser;
import iservice.IBsUserService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制类
 */

public class BsUserAction extends BsBaseAction {

    private static final long serialVersionUID = 1L;
    private final static int PAGE_SIZE = 10;
    private int ADMIN_RIGHT = 2;  // 管理员权限
    private IBsUserService userService = (IBsUserService) BsFactory.getBean("userService");

    private BsUser user;
    private Integer userId;
    private BsPageList<BsUser> pageList;  // 分页器
    private Integer pageNo;
    private String msg;  // 返回信息

    // 管理用户
    @Override
    protected void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (pageNo == null) {
            pageNo = 1;
            System.out.println("这里是默认赋值首页");
        }

        if (request.getParameter("pageNo") != null) {
            pageNo = Integer.parseInt(request.getParameter("pageNo"));  // 获取跳转的页数
        }

        try {
            int count = userService.findCount();
            List<BsUser> list = userService.findUsers(pageNo, PAGE_SIZE);  // 分页查找用户数据
            pageList = new BsPageList<>(list, count, PAGE_SIZE, pageNo, "/bs/BsUserAction?method=manage");
            request.setAttribute("pageList", pageList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/manage.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }

    }

    // 删除用户
    @Override
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userId = Integer.parseInt(request.getParameter("userId"));

        try {
            userService.deleteUser(userId);

            request.setAttribute("msg", "删除成功!<a href=\"/bs/BsUserAction?method=manage\" target=\"top\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);

        } catch (MyException e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    // 显示用户
    @Override
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userId = Integer.parseInt(request.getParameter("userId"));

        try {
            BsUser user = userService.findUserById(userId);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/show.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 登录用户
    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BsUser userInfo = getUserInfo(request);

        try {
            BsUser user = userService.findUserByName(userInfo.getUserName(), userInfo.getUserPwd());  // 查找用户
            HttpSession session = request.getSession();

            session.setAttribute("user", user);

            if (user.getUserRight() >= ADMIN_RIGHT) {  // 管理员权限
                request.setAttribute("msg", "登录成功!<a href=\"/bs/admin/index.jsp\" target=\"top\">返回首页</a>");
            } else {
                request.setAttribute("msg", "登录成功!<a href=\"/bs/index.jsp\" target=\"top\">返回首页</a>");
            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", "登录失败,请检查账号或密码是否一致" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }

    }

    // 执行修改信息
    @Override
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            user = userService.findUserById(Integer.parseInt(request.getParameter("userId")));
            user.setUserName(request.getParameter("userName"));
            user.setUserRealName(request.getParameter("userRealName"));
            user.setUserPhone(request.getParameter("userPhone"));
            user.setUserEmail(request.getParameter("userEmail"));
            user.setUserAddr(request.getParameter("userAddr"));

            userService.editUser(user);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.setAttribute("msg", "修改成功!<a href=\"/bs/index.jsp\" target=\"top\">返回首页</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 添加用户（用户注册）
    @Override
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            user = getUserInfo(request);
            userService.addUser(user);

            request.setAttribute("msg", "注册成功!<a href=\"/bs/user/login.jsp\" target=\"top\">立即登录</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 获取用户信息
    private BsUser getUserInfo(HttpServletRequest request) {

        user = new BsUser();
        user.setUserName(request.getParameter("userName"));
        user.setUserPwd(request.getParameter("password"));
        user.setUserRealName(request.getParameter("realName"));
        user.setUserPhone(request.getParameter("phone"));
        user.setUserEmail(request.getParameter("email"));
        user.setUserAddr(request.getParameter("address"));

        return user;
    }

}
