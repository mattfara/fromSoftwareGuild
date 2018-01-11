/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Address;
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
 * @Address matt
 */
public class AddressDaoDbImpl implements AddressDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //ADAPT
    private static String SQL_INSERT_ADDRESS = "INSERT INTO Address (Street, City, State, Zipcode) VALUES (?, ?, ?, ?)";
    private static String SQL_GET_ADDRESS = "SELECT * FROM Address WHERE AddressId = ?";
    private static String SQL_UPDATE_ADDRESS = "UPDATE Address SET Street = ?, City = ?, State = ?, Zipcode = ? WHERE AddressId = ?";
    private static String SQL_DELETE_ADDRESS = "DELETE FROM Address WHERE AddressId = ?";
    private static String SQL_LIST_ADDRESSES = "SELECT * FROM Address Limit ?, ?";

//    private static String SQL_LIST_ADDRESSES_BY_BOOK = "SELECT * FROM Address a"
//            + "INNER JOIN Address_book ab ON ab.Address_id = a.id"
//            + "WHERE ab.book_id = ? limit ?,?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Address createAddress(Address address) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getStreet(), //THIS LIST MUST BE ORDERED BY PREPARED STATEMENT
                address.getCity(),
                address.getState(),
                address.getZipcode());
        
        int addressId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        address.setAddressId(addressId);

        return address;
    }

    @Override
    public Address getAddressById(Integer addressId) {
        try {
            Address address = 
                    jdbcTemplate.queryForObject(SQL_GET_ADDRESS, new AddressMapper(), addressId);
            return address;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Address> getAllAddresses(int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_ADDRESSES, new AddressMapper(), offset, limit);
    }

    @Override
    public Address updateAddress(Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS, 
                address.getStreet(),
                address.getCity(), 
                address.getState(), 
                address.getZipcode(),
                address.getAddressId()
                );

        return address;
    }

    @Override
    public Address deleteAddress(Address address) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, address.getAddressId());
        return address;
    }

    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {
            Address ad = new Address();
            ad.setStreet(rs.getString("Street"));
            ad.setCity(rs.getString("City"));
            ad.setState(rs.getString("State"));
            ad.setZipcode(rs.getString("Zipcode"));
            ad.setAddressId(rs.getInt("addressId"));

            return ad;
        }
    }

}
