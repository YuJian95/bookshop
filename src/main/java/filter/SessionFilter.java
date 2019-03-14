/*
package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

*/
/**
 * 拦截器,转码的后, 验证用户是否已经登录.
 *//*


@WebFilter(filterName = "SessionFilter",
        urlPatterns = {"/*"},  // 拦截该项目下的所有用户请求
        initParams = {  // 配置Filter初始化参数
                @WebInitParam(name = "encoding", value = "UTF-8"),
                @WebInitParam(name = "loginPage", value = "/login.jsp"),
                @WebInitParam(name = "message", value = "/message.jsp"),
                @WebInitParam(name = "index", value = "/index.jsp")}
)
public class SessionFilter implements Filter {
    private FilterConfig config;  // 用于配置访问信息

    public void destroy() {
        this.config = null;
    }

    //执行拦截
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        //获取Filter的配置参数
        String encoding = config.getInitParameter("encoding");
        String loginPage = config.getInitParameter("loginPage");
        String messagePage = config.getInitParameter("message");
        String indexPage = config.getInitParameter("index");

        request.setCharacterEncoding(encoding);

        //获取客户请求的页面
        String requestPath = request.getServletPath();
        System.out.println(requestPath);
        String userName = (String) session.getAttribute("userName");
        System.out.println(userName);//测试是否可以执行.

        if (userName == null && !requestPath.endsWith(loginPage) && !requestPath.equals(indexPage) && !requestPath.endsWith(messagePage)) {  // 当用户没有登录时
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    //初始化
    public void init(FilterConfig config) {
        this.config = config;
    }

}
*/
