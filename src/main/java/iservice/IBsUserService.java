package iservice;

import domain.BsUser;

import java.util.List;

/**
 * 用户业务逻辑接口
 */

public interface IBsUserService {

    void addUser(BsUser user);

    void editUser(BsUser user);

    void deleteUser(Integer userId);

    BsUser findUserById(Integer userId);

    BsUser findUserByName(String userName, String userPwd);

    String findUser(String userName, String userEmail);

    List<BsUser> findUsers(Integer pageNo, Integer pageSize);

    Integer findCount();

}
