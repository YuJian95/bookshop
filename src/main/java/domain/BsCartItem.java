package domain;


/**
 * 购物车条目
 */

import exception.MyException;

import javax.persistence.*;

@Entity
@Table(name = "bs_cart", schema = "bs", catalog = "bs")
public class BsCartItem implements java.io.Serializable {

    private Integer userId;
    private BsBook book;
    private Integer num;
    private Integer cartId;
    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        if (num <= 0 || book.getBookPrice() <= 0) {
            throw new MyException("数量或价格为空！");
        } else {
            total = num * book.getBookPrice();
        }
    }

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
