package action;

import common.BsFactory;
import common.BsPageList;
import dao.BsOrderDao;
import domain.BsOrder;
import iservice.IBsOrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BsOrderAction extends BsBaseAction {
    private final int PAGE_SIZE = 6;
    private IBsOrderService orderService = (IBsOrderService) BsFactory.getBean("orderService");
    private BsOrder order;
    private Integer ordId;
    private Integer userId;
    private Integer ordState;
    private BsPageList<BsOrder> pageList;
    private Integer pageNo;
    private String msg;

    @Override
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "订单添加:";

        try {

            userId = Integer.parseInt(request.getParameter("userId"));
            ordState = Integer.parseInt(request.getParameter("ordState"));
            order = new BsOrder();
            order.setUserId(userId);
            order.setOrdState(ordState);
            orderService.addOrder(order);

            request.setAttribute("msg", msg + "成功" + "<a href =\"/book/manage.jsp\" target=\"top\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }


    @Override
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "订单修改:";

        try {

            userId = Integer.parseInt(request.getParameter("userId"));
            ordState = Integer.parseInt(request.getParameter("ordState"));
            order = new BsOrder();
            order.setUserId(userId);
            order.setOrdState(ordState);
            orderService.editOrder(order);

            request.setAttribute("msg", msg + "成功" + "<a href =\"/book/manage.jsp\" target=\"top\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 订单删除
    @Override
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "删除订单:";
        try {
            ordId = Integer.parseInt(request.getParameter("ordId"));
            orderService.deleteOrder(ordId);
            request.setAttribute("msg", msg + "成功" + "<a href =\"/order/manage.jsp\" target=\"top\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    // 订单浏览
    @Override
    protected void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (pageNo == null) {
            pageNo = 1;
        }
        userId = Integer.parseInt(request.getParameter("userId"));

        try {
            int count = orderService.findCount(userId);
            List<BsOrder> list = orderService.findOrders(userId, pageNo, PAGE_SIZE);  // 分页查找订单
            pageList = new BsPageList<>(list, count, PAGE_SIZE, pageNo, "/bs/BsOrderAction?method=browse");

            request.setAttribute("pageList", pageList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/order/browsejsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 订单编辑
    @Override
    protected void willEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        msg = "编辑订单:";
        try {
            userId = Integer.parseInt(request.getParameter("userId"));
            ordId = Integer.parseInt(request.getParameter("ordId"));
            order = new BsOrderDao().selectById(ordId);
            request.setAttribute("order", order);
            request.setAttribute("userId", userId);
            request.setAttribute("ordId", ordId);

            response.sendRedirect("/order/edit.jsp");
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    // 分页查看订单
    @Override
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "查看订单:";
        if (pageNo == null) {
            pageNo = 1;
        }
        userId = Integer.parseInt(request.getParameter("userId"));

        try {
            ordId = Integer.parseInt(request.getParameter("ordId"));
            order = new BsOrderDao().selectById(ordId);
            request.setAttribute("order", order);
            request.setAttribute("userId", userId);
            request.setAttribute("ordId", ordId);

            response.sendRedirect("/order/show.jsp");
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    // 订单管理
    @Override
    protected void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (pageNo == null) {
            pageNo = 1;
        }

        try {
            int count = orderService.findCount(null);
            List<BsOrder> list = orderService.findOrders(null, pageNo, PAGE_SIZE);  // 分页查找订单
            pageList = new BsPageList<>(list, count, PAGE_SIZE, pageNo, "/bs/BsOrderAction?method=manage");

            request.setAttribute("pageList", pageList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/order/manage.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage() + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }
}
