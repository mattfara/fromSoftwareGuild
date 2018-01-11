/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.addressbook.dao;

import com.tsguild.basics.addressbook.dto.Address;
import com.tsguild.basics.addressbook.dto.Person;
import com.tsguild.basics.addressbook.dto.PersonAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author matt
 */
//For bridge table -- 
public class PersonAddressDaoJdbcTemplateImpl {

    private JdbcTemplate jdbcTemplate;
    
    @Inject //be sure to place the beans properly....
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //MY SQL STATEMENTS
    private static final String SQL_COUNT_ENTRIES = "select count(*) from person_address;";
    
    //METHODS
    public int countEntries(){
        return jdbcTemplate.queryForObject(SQL_COUNT_ENTRIES, Integer.class);
    }
    
    
    private static final class PersonAddressMapper implements RowMapper<PersonAddress> {
        
        @Override
        public PersonAddress mapRow(ResultSet rs, int i) throws SQLException {
            
            PersonAddress pa = new PersonAddress();
            Person person = new Person();
            person.setPersonId(rs.getInt("person_id"));
            
            Address address = new Address();
            address.setAddressId(rs.getInt("address_id"));
            
            pa.setPersonAddressId(rs.getInt("id"));
            
            return pa;
        }
        
    }
    
}
