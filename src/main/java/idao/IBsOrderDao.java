package idao;


import domain.BsOrder;

import java.util.List;


/**
 * 订单数据访问层接口
 */

public interface IBsOrderDao extends IBsBaseDao<BsOrder, Integer> {
    List<BsOrder> selectSome(Integer userId, Integer pageNo, Integer pageSize);

    int selectCount(Integer userId);
}
