package service;

import common.BsFactory;
import domain.BsUser;
import idao.IBsUserDao;
import iservice.IBsUserService;

import java.util.List;

public class BsUserService implements IBsUserService {

    private IBsUserDao userDao = (IBsUserDao) BsFactory.getBean("userDao");

    @Override
    public void addUser(BsUser user) {
        userDao.insert(user);
    }

    @Override
    public void editUser(BsUser user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userDao.delete(userId);
    }

    @Override
    public BsUser findUserById(Integer userId) {
        return userDao.selectById(userId);
    }

    @Override
    public BsUser findUserByName(String userName, String userPwd) {
        return userDao.selectOne(userName, userPwd);
    }

    @Override
    public String findUser(String userName, String userEmail) {
        return userDao.selectPwd(userName, userEmail);
    }

    @Override
    public List<BsUser> findUsers(Integer pageNo, Integer pageSize) {
        return userDao.selectAll(pageNo, pageSize);
    }

    @Override
    public Integer findCount() {
        return userDao.selectAllCount();
    }
}
