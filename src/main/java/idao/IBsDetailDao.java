package idao;

import domain.BsDetails;

/**
 * 订单详情数据访问接口
 */

public interface IBsDetailDao extends IBsBaseDao<BsDetails, Integer> {
    void deleteSome(Integer ordId);
}
