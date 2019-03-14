package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "EncodeFilter", dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD,
        DispatcherType.INCLUDE,
        DispatcherType.ERROR},
        urlPatterns = {"/*"})//对网站下所有页面进行中文过滤
public class EncodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req1 = (HttpServletRequest) req;
        HttpServletResponse resp1 = (HttpServletResponse) resp;
        req1.setCharacterEncoding("utf-8");
        resp1.setCharacterEncoding("utf-8");
        resp1.setContentType("text/html;charset=utf-8");
        chain.doFilter(req1, resp1);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
