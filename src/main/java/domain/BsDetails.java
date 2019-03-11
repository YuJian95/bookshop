package domain;

import javax.persistence.*;

@Entity
@Table(name = "bs_details", schema = "bs", catalog = "bs")
public class BsDetails implements java.io.Serializable {
    private int detId;
    private int ordId;
    private int bookId;
    private double detNum;
    private BsBook book;

    public BsDetails() {
    }

    public BsDetails(int detId, int ordId, int bookId, double detNum, BsBook book) {
        this.detId = detId;
        this.ordId = ordId;
        this.bookId = bookId;
        this.detNum = detNum;
        this.book = book;
    }

    @Id
    @Column(name = "det_id")
    public int getDetId() {
        return detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    @Basic
    @Column(name = "ord_id")
    public int getOrdId() {
        return ordId;
    }

    public void setOrdId(int ordId) {
        this.ordId = ordId;
    }

    @Basic
    @Column(name = "book_id")
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "det_num")
    public double getDetNum() {
        return detNum;
    }

    public void setDetNum(double detNum) {
        this.detNum = detNum;
    }

    public BsBook getBook() {
        return book;
    }

    public void setBook(BsBook book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BsDetails{" +
                "detId=" + detId +
                ", ordId=" + ordId +
                ", bookId=" + bookId +
                ", detNum=" + detNum +
                ", book=" + book +
                '}';
    }
}
