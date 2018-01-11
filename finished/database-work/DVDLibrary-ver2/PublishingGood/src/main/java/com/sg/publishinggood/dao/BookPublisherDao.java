/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.dao;

import com.sg.publishinggood.dto.BookPublisher;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author matt
 */


//better to think of this as its own thing, not some extension
//
public class BookPublisherDao {
    
    private JdbcTemplate jdbcTemplate; //can we use @Inject or @Autowired here?
    
    public BookPublisher get(Integer id){return null;}
    public List<BookPublisher> list(){return null;}
    public void  update(BookPublisher authorBook){}
    public void delete (BookPublisher authorBook){}
    public BookPublisher create(BookPublisher authorBook) {return null;}
}
