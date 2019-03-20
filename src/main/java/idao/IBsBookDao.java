package idao;

import domain.BsBook;


import java.util.List;

/**
 * 图书数据访问层接口
 */

public interface IBsBookDao extends IBsBaseDao<BsBook, Integer> {
    List<BsBook> selectSome(Integer catId, String bookName, String bookAuthor, Integer pageNo, Integer pageSize);

    int selectCount(Integer catId, String bookName, String bookAuthor);

    void editBook(Integer bookId, Integer isCarousel);

    int selectSomeCount(Integer catId);

    BsBook selectBook(String bookName);

    List<BsBook> selectCarouselBook();
}
