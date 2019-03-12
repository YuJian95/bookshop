package action;

import common.BsFactory;
import common.BsPageList;
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
    private BsPageList<BsOrder> pageList;
    private Integer pageNo;
    private String msg;

    @Override
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.add(request, response);
    }

    @Override
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.edit(request, response);
    }

    @Override
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.delete(request, response);
    }

    @Override
    protected void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.browse(request, response);
    }

    @Override
    protected void willEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.willEdit(request, response);
    }

    @Override
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.show(request, response);
    }

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
