package service;

import common.BsFactory;
import domain.BsBook;
import idao.IBsBookDao;
import iservice.IBsBookService;

import java.util.List;

public class BsBookService implements IBsBookService {

    private IBsBookDao bookDao = (IBsBookDao) BsFactory.getBean("bookDao");

    @Override
    public void addBook(BsBook book) {
        bookDao.insert(book);
    }

    @Override
    public void editBook(BsBook book) {
        bookDao.update(book);
    }

    @Override
    public void deleteBook(Integer bookId) {
        bookDao.delete(bookId);
    }

    @Override
    public BsBook findBookById(Integer bookId) {
        return bookDao.selectById(bookId);
    }

    @Override
    public List<BsBook> findBooks(Integer catId, String bookName, String bookAuthor, Integer pageNo, Integer pageSize) {
        return bookDao.selectSome(catId, bookName, bookAuthor, pageNo, pageSize);
    }

    @Override
    public int findCount(Integer catId, String bookName, String bookAuthor) {
        return bookDao.selectCount(catId, bookName, bookAuthor);
    }

    @Override
    public List<BsBook> findBooks() {
        return bookDao.selectAll();
    }

    @Override
    public int findAllCount() {
        return bookDao.selectAllCount();
    }

}
