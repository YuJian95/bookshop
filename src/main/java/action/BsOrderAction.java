package action;

import common.BsFactory;
import common.BsPageList;
import dao.BsOrderDao;
import domain.BsCartItem;
import domain.BsDetails;
import domain.BsOrder;
import domain.BsUser;
import iservice.IBsCartService;
import iservice.IBsOrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BsOrderAction extends BsBaseAction {
    private final int PAGE_SIZE = 6;
    private IBsOrderService orderService = (IBsOrderService) BsFactory.getBean("orderService");
    private IBsCartService cartService = (IBsCartService) BsFactory.getBean("cartService");
    // private BsOrder order;
    private Integer ordId;
    private Integer userId;
    private Integer ordState;
    private BsPageList<BsOrder> pageList;
    private Integer pageNo;
    private String msg;

    // 订单添加
    @Override
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "订单添加:";

        try {
            BsUser user = (BsUser) request.getSession().getAttribute("user");
            userId = user.getUserId();
            BsOrder order = new BsOrder();
            int ordId = Integer.valueOf(String.valueOf(new java.util.Date().getTime()).substring(0, 10));
            order.setOrdId(ordId);

            List<BsCartItem> cartItems = cartService.findItems(userId);
            for (BsCartItem cartItem : cartItems) {
                BsDetails details = new BsDetails();
                //System.out.println("开始" + cartItem.getBook());
                //System.out.println("测试" + cartItem.getNum());
                details.setBook(cartItem.getBook());
                details.setDetNum(cartItem.getNum());
                details.setOrdId(ordId);
                details.setBookId(cartItem.getBook().getBookId());
                order.getBsDetailses().add(details);
            }
            order.setUserId(userId);
            order.setUser(user);
            orderService.addOrder(order);
            cartService.clear(userId);//  清空购物车
            request.setAttribute("msg", msg + "成功" + "<a href =\"/bs/index.jsp\" target=\"top\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 订单修改
    @Override
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "订单修改:";

        try {
            BsOrder order = new BsOrder();
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
        pageNo = 1;
        if (request.getParameter("pageNo") != null) {
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        }

        try {

            BsUser user = (BsUser) request.getSession().getAttribute("user");
            userId = user.getUserId();

            int count = orderService.findCount(userId);
            List<BsOrder> list = orderService.findOrders(userId, pageNo, PAGE_SIZE);  // 分页查找订单
            pageList = new BsPageList<>(list, count, PAGE_SIZE, pageNo, "/bs/BsOrderAction?method=browse");
            request.setAttribute("pageList", pageList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/order/browse.jsp");
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
        BsOrder order = new BsOrder();
        try {
            userId = Integer.parseInt(request.getParameter("userId"));
            ordId = Integer.parseInt(request.getParameter("ordId"));
            order = new BsOrderDao().selectById(ordId);
            request.setAttribute("order", order);
            request.setAttribute("userId", userId);
            request.setAttribute("ordId", ordId);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/order/edit.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    protected void manageEditState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        msg = "修改订单状态:";
        BsOrder order = new BsOrder();
        try {

            ordId = Integer.parseInt(request.getParameter("ordId"));
            order.setOrdId(ordId);
            order.setOrdState(1);// 已发货
            orderService.editOrder(order);

            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");
            requestDispatcher.forward(request, response);
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
        pageNo = 1;
        if (request.getParameter("pageNo") != null) {
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        }
        try {
            BsUser user = (BsUser) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect("/bs/user/login.jsp");
            }
            userId = user.getUserId();

            ordId = Integer.parseInt(request.getParameter("ordId"));
            BsOrder order;
            order = new BsOrderDao().selectById(ordId);
            request.setAttribute("order", order);
            request.setAttribute("userId", userId);
            request.setAttribute("ordId", ordId);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/order/show.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    // 订单管理
    @Override
    protected void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        pageNo = 1;
        if (request.getParameter("pageNo") != null) {
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        }

        try {
            int count = orderService.findAllCount();
            List<BsOrder> list = orderService.findAllOrders(pageNo, PAGE_SIZE);  // 分页查找订单
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
