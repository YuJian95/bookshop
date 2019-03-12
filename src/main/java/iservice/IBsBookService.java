package iservice;

import domain.BsBook;

import java.util.List;

/**
 * 图书业务逻辑层接口
 */

public interface IBsBookService {
    void addBook(BsBook book);

    void editBook(BsBook book);

    void deleteBook(Integer bookId);

    BsBook findBookById(Integer bookId);

    List<BsBook> findBooks(Integer catId, String bookName, String bookAuthor, Integer pageNo, Integer pageSize);

    List<BsBook> findBooks();

    int findCount(Integer catId, String bookName, String bookAuthor);

    int findAllCount();

    List<BsBook> findSomeById(Integer catId);

    int findSomeCount(Integer catId);

}
