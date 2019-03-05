package idao;

import domain.BsUser;

/**
 * 用户数据访问层接口
 */

public interface IBsUserDao extends IBsBaseDao<BsUser, Integer> {
    BsUser selectOne(String userName, String userPwd);

    String selectPwd(String userName, String userEmail);
}
