package domain;

import javax.persistence.*;

@Entity
@Table(name = "bs_book", schema = "bs", catalog = "bs")
public class BsBook implements java.io.Serializable {

    private Integer bookId;
    private BsCategory Category;
    private String bookPublisher;
    private String bookIsbn;
    private String bookName;
    private String bookPicture;
    private double bookPrice;
    private String bookAuthor;
    private String bookDesc;
    private Integer bookNum;

    @Id
    @Column(name = "book_id")
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "book_publisher")
    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    @Basic
    @Column(name = "book_isbn")
    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    @Basic
    @Column(name = "book_name")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "book_picture")
    public String getBookPicture() {
        return bookPicture;
    }

    public void setBookPicture(String bookPicture) {
        this.bookPicture = bookPicture;
    }

    @Basic
    @Column(name = "book_price")
    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Basic
    @Column(name = "book_author")
    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Basic
    @Column(name = "book_desc")
    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    @Basic
    @Column(name = "book_num")
    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public BsCategory getCategory() {
        return Category;
    }

    public void setCategory(BsCategory category) {
        Category = category;
    }
}
