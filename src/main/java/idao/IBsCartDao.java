package idao;

import domain.BsCartItem;

public interface IBsCartDao extends IBsBaseDao<BsCartItem, Integer> {
    public void update(BsCartItem bsCartItem, int num);

    public void clear(Integer userId);

    public int selectAllCount(Integer userId);

    public float calTotal(Integer userId);
}
