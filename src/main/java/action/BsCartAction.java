package action;

import common.BsFactory;
import common.BsPageList;
import dao.BsCartDao;
import domain.BsBook;
import domain.BsCartItem;
import domain.BsUser;
import iservice.IBsCartService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BsCartAction extends BsBaseAction {
    private final int PAGE_SIZE = 6;

    private IBsCartService cartService = (IBsCartService) BsFactory.getBean("cartService");
    private Integer userId;
    private Integer pageNo;
    private Integer cartId;
    private Integer bookId;
    private BsPageList<BsCartItem> pageList;
    private BsCartItem cartItem;
    private String msg;
    private HttpSession session;

    @Override
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cartItem = new BsCartItem();
        msg = "添加购物车:";

        try {
            session = request.getSession();
            BsBook book = new BsBook();
            book.setBookId(Integer.parseInt(request.getParameter("bookId")));
            BsUser user = (BsUser) session.getAttribute("user");
            userId = user.getUserId();
            cartItem.setBook(book);
            cartItem.setNum(1);
            cartItem.setUserId(userId);
            cartService.addItem(cartItem);
            request.setAttribute("msg", msg + "成功" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "编辑购物车:";

        try {
            BsUser user = (BsUser) request.getSession().getAttribute("user");
            cartId = Integer.parseInt(request.getParameter("cartId"));
            cartItem = new BsCartDao().selectById(cartId);
            int num = Integer.parseInt(request.getParameter("num"));
            cartService.editItem(cartItem, num);

            request.setAttribute("msg", msg + "成功" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 查看购物车
    @Override
    protected void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "查看购物车:";

        try {
            BsUser user = (BsUser) request.getSession().getAttribute("user");

            List<BsCartItem> cartList = cartService.findItems(user.getUserId());
            request.setAttribute("cartList", cartList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/cart/browse.jsp");
            requestDispatcher.forward(request, response);
            System.out.println("转发失败2");
        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.show(request, response);
    }

    // 清空购物车
    @Override
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "清空购物车:";

        try {
            BsUser user = (BsUser) request.getSession().getAttribute("user");

            cartService.clear(user.getUserId());
            request.setAttribute("msg", msg + "成功" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }

    // 删除购物车中物品
    @Override
    protected void deleteSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msg = "删除商品:";

        try {
            cartId = Integer.parseInt(request.getParameter("catId"));
            BsUser user = (BsUser) request.getSession().getAttribute("user");
            cartService.deleteItem(cartId);
            request.setAttribute("msg", msg + "成功" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/message.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", msg + "失败" + "<a href=\"JavaScript:window.history.back()\">返回</a>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/error.jsp");  // 跳转到信息页
            requestDispatcher.forward(request, response);
        }
    }
}
