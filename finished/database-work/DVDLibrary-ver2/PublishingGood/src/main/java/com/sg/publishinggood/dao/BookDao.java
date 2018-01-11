/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.dao;

import com.sg.publishinggood.dto.Author;
import com.sg.publishinggood.dto.Book;
import com.sg.publishinggood.dto.Publisher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author matt
 */
public class BookDao {

    private JdbcTemplate jdbcTemplate; //can we use @Inject or @Autowired here?

    private static final String SQL_INSERT_BOOK = "insert into books (isbn, title, publisher_id, price, publish_date) values (?,?,?,?,?)"; //remember that these column names don't always correspond perfectly to field names in DTOs
    //for the bridge table...

    private static final String SQL_DELETE_BOOK
            = "delete from books where book_id = ?";

    private static final String SQL_UPDATE_BOOK
            = "update books set isbn = ?, title = ?, publisher_id = ?, "
            + "price = ?, publish_date = ? "
            + "where book_id = ?";

    private static final String SQL_SELECT_BOOK
            = "select * from books where book_id = ?";

    private static final String SQL_SELECT_ALL_BOOKS
            = "select * from books";

    private static final String SQL_SELECT_BOOKS_BY_PUBLISHER = "select book.`name`, publisher.`name`"
            + "from book"
            + "inner join book_publisher bp on bp.book_id = book.id"
            + "inner join publisher on bp.publisher_id = publisher.id"
            + "where publisher.id = ?;";
    
    private static final String SQL_LIST_BOOKS_BY_AUTHOR = "select b.* from book b"
            + "inner join author_book ab on ab.book_id = b.id"
            + "where ab.author_id = ? limit ?, ?;";
    
    
    public Book get(Integer id) {
        return null;
    }

    public List<Book> list() {
        return null;
    }

    public void update(Book book) {
    }

    public void delete(Book book) {
    }

    public Book create(Book book) {
        return null;
    }

    public List<Book> getBooksByPublisher(Publisher p) {
        return null;
    }

    public List<Book> getBooksByAuthor(Author a, int offset, int limit) {
            return jdbcTemplate.query(SQL_LIST_BOOKS_BY_AUTHOR, new BookMapper(), a.getId(), offset, limit);
    }

    
    private static final class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int i) throws SQLException {
            Book b = new Book();
            b.setName(rs.getString("name"));
            b.setId(rs.getInt("id"));

            return b;
        }
    }

}
