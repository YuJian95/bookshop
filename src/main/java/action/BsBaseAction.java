package action;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 控制层基类
 */

@WebServlet(name = "BsBaseAction")
public abstract class BsBaseAction extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);  // 检查是否是multipart表单

//        if (isMultipart) {
//            uploadFile(request, response);  // 添加
//        } else {
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
        } else if (method.equals("login")) {
            login(request, response);   //登录
        } else if (method.equals("clear")) {
            clear(request, response);  // 清空
        } else if (method.equals("deleteSome")) {
            deleteSome(request, response);
        }
    }
//    }

    protected void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void willEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void deleteSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
