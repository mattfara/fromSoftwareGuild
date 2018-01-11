/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.dao;

import com.sg.publishinggood.dto.Publisher;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author matt
 */
public class PublisherDao { //would normally have an interface...skipping for time
    
    private JdbcTemplate jdbcTemplate; //can we use @Inject or @Autowired here?
    
        private static final String SQL_INSERT_PUBLISHER
            = "insert into publishers (name, street, city, state, zip, phone) "
            + "values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_PUBLISHER
            = "delete from publishers where publisher_id = ?";
    private static final String SQL_UPDATE_PUBLISHER
            = "update publishers set name = ?, street = ?, city = ?, "
            + "state = ?, zip = ?, phone = ? where publisher_id  =  ?";
    private static final String SQL_SELECT_PUBLISHER
            = "select * from publishers where publisher_id = ?";
    
    private static final String SQL_SELECT_ALL_PUBLISHERS
            = "select * from publishers";
    //end publisher related prepared statements

    
    
    public Publisher get(Integer id){return null;}
    public List<Publisher> list(){return null;}
    public void  update(Publisher publisher){}
    public void delete (Publisher publisher){}
    public Publisher create(Publisher publisher) {return null;}
}
