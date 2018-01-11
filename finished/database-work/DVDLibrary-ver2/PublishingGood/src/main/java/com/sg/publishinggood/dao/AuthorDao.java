/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.dao;

import com.sg.publishinggood.dto.Author;
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
 * @author matt
 */
public class AuthorDao {

    private JdbcTemplate jdbcTemplate; //can we use @Inject or @Autowired here?

    private static final String SQL_INSERT_AUTHOR = "insert into author (name) values (?)";
    private static final String SQL_DELETE_AUTHOR = "delete from author where id = ?";
    private static final String SQL_UPDATE_AUTHOR = "update author set name = ? where id = ?";
    private static final String SQL_GET_AUTHOR = "select * from author where id = ?";
    private static final String SQL_GET_ALL_AUTHORS = "select * from author";
    private static final String SQL_GET_AUTHORS_BY_PUBLISHER = "select publisher.`name`, author.`name`"
            + "from author"
            + "inner join author_book ab on ab.author_id = author.id"
            + "inner join book_publisher bp on bp.book_id = ab.book_id"
            + "inner join publisher on publisher.id = bp.publisher_id"
            + "where publisher.id = ?;";

    private static final String SQL_LIST_AUTHORS_BY_BOOK = "select a.* from author a"
            + "inner join author_book ab on ab.author_id = a.id"
            + "where ab.book_id = ? limit ?, ?;";
    
    public Author get(Integer id) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_AUTHOR, new AuthorMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Author> list() {
        return jdbcTemplate.query(SQL_GET_ALL_AUTHORS, new AuthorMapper());
    }

    public void update(Author author) {
        jdbcTemplate.update(SQL_UPDATE_AUTHOR, author.getName(), author.getId());
    }

    public void delete(Author author) {
        jdbcTemplate.update(SQL_DELETE_AUTHOR, author.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Author create(Author author) { //same as 
        jdbcTemplate.update(SQL_INSERT_AUTHOR,
                author.getName());
        Integer authorId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        author.setId(authorId);

        return author;
    }

    public List<Author> getAuthorsByBook (Book book, int offset, int limit){
        return jdbcTemplate.query(SQL_LIST_AUTHORS_BY_BOOK, new AuthorMapper(), book.getId(), offset, limit);
    }
    
    
    private static final class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            Author au = new Author();
            au.setName(rs.getString("name"));
            au.setId(rs.getInt("id"));

            return au;
        }
    }

}
