package domain;

import action.BsBaseAction;

/**
 * 购物车条目
 */

public class BsCartItem implements java.io.Serializable {

    private Integer userId;
    private BsBook book;
    private Integer num;
    private Integer cartId;

    public BsCartItem() {
    }

    public BsBook getBook() {
        return book;
    }

    public void setBook(BsBook book) {
        this.book = book;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "BsCartItem{" +
                "userId=" + userId +
                ", book=" + book.getBookId() +
                ", num=" + num +
                ", cartId=" + cartId +
                '}';
    }
}
