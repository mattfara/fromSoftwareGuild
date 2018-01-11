/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.dao;

import com.sg.publishinggood.dto.Author;
import com.sg.publishinggood.dto.AuthorBook;
import com.sg.publishinggood.dto.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author_book matt
 */
public class AuthorBookDao {

    private JdbcTemplate jdbcTemplate; //can we use @Inject or @Autowired here?

    //check db for stuff you need
    private static final String SQL_INSERT_AUTHOR_BOOK = "insert into author_book (author_id, book_id) values (?, ?)";
    private static final String SQL_DELETE_AUTHOR_BOOK = "delete from author_book where id = ?";
    private static final String SQL_UPDATE_AUTHOR_BOOK = "update author_book set author_id = ?, book_id = ? where id = ?";
    private static final String SQL_GET_AUTHOR_BOOK = "select * from author_book where id = ?";
    private static final String SQL_LIST_AUTHOR_BOOKS = "select * from author_book";
    
    //CRUD IS NOW POSSIBLE FOR ALL OF THESE DTOs
    
    
    public AuthorBook get(Integer id) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_AUTHOR_BOOK, new AuthorBookMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<AuthorBook> list() {
        return jdbcTemplate.query(SQL_LIST_AUTHOR_BOOKS, new AuthorBookMapper());
    }

    public void update(AuthorBook authorBook) {
        
        if (authorBook.getAuthor() == null || authorBook.getBook() == null) {return;}
        
        jdbcTemplate.update(SQL_UPDATE_AUTHOR_BOOK, authorBook.getAuthor().getId(), 
                authorBook.getBook().getId(), 
                authorBook.getId());
    }

    public void delete(AuthorBook authorBook) {
        jdbcTemplate.update(SQL_DELETE_AUTHOR_BOOK, authorBook.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public AuthorBook create(AuthorBook authorBook) {
        jdbcTemplate.update(SQL_INSERT_AUTHOR_BOOK,
                authorBook.getAuthor().getId(),
                authorBook.getBook().getId()
        );
        
        Integer authorBookId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        authorBook.setId(authorBookId);
        
        return authorBook;
    }

    private static final class AuthorBookMapper implements RowMapper<AuthorBook> {

        @Override
        public AuthorBook mapRow(ResultSet rs, int i) throws SQLException {
            AuthorBook authorBook = new AuthorBook();

            //big decision point for design of queries and daos....
            //do we also load actual author and book, or do we do lazy loading? eager or lazy? eager uses joins in a single query
            //lazy loading is where another method adds fields later...
            //the objects only get ids, and if the service wants the actual author and book, the ids are used
            //alternatively, use joins in query (eager fetching)
            //good idea to learn both ways
            //eager is slightly faster since it has fewer queries involved....
            //the extra queries are not slow usually...but if things turn out slow later, change it
            
            //showing lazy load author and book
                //author
            Author author = new Author();
            author.setId(rs.getInt("author_id")); //we pull this from bridge table
                //book
            Book book = new Book();
            book.setId(rs.getInt("book_id")); //we pull this from bridge table
            
            
            authorBook.setId(rs.getInt("id"));
            
            
            return authorBook;
        }
    }

    
}

//need of list of books by author
//need of list of authors by book
//these are done in their respective daos and service pass throughs

//service
//a service will create a new AuthorBook relationship
//remove a relationship 
//update too if complex relationship (if it has more than just ids)

//service will take in an author and book -- doesnt' know about bridge table
    //it creates an AuthorBook and saves to Dao