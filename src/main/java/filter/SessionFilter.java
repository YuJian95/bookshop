package filter;

import domain.BsUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 拦截器,验证用户是否已经登录.
 */

@WebFilter(filterName = "SessionFilter")
public class SessionFilter implements Filter {
    public FilterConfig config;

    public void destroy() {
        this.config = null;
    }

    //执行拦截
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        BsUser user = (BsUser) session.getAttribute("user");
        System.out.println(user.getUserName());//测试是否可以执行.

        if (user == null) {
            response.sendRedirect("user/login.jsp");
        }

        chain.doFilter(req, resp);
    }

    //初始化
    public void init(FilterConfig config) throws ServletException {

    }

}
