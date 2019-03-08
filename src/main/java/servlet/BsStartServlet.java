package servlet;

import common.BsConfig;
import exception.MyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 启动时，读取配置信息
 */

public class BsStartServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            String path = this.getServletContext().getRealPath("WEB-INF/config.properties");
            InputStream in = new FileInputStream(path);
            BsConfig.properties.load(in);

            if (BsConfig.properties.isEmpty()) {
                throw new MyException("读取配置信息出错！");
            }

        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

}
