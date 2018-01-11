/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.addressbook.dao;

import com.tsguild.basics.addressbook.dto.Address;
import com.tsguild.basics.addressbook.dto.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matt
 */
public class AddressDaoJdbcTemplateImpl {

    private JdbcTemplate jdbcTemplate;

    @Inject //place beans properly
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_CREATE_ADDRESS = "insert into Address (street_address, city, state, zipcode) values (?, ?, ?, ?)";
    private static final String SQL_DELETE_ADDRESS = "delete from Address where addressId = ?";
    private static final String SQL_UPDATE_ADDRESS = "update Address set first_name = ?, last_name = ?, phone = ?, email = ? where addressId = ?";
    private static final String SQL_GET_ADDRESS = "select * from Address where addressId = ?";
    private static final String SQL_LIST_ADDRESSES = "select * from Address";
    private static final String SQL_GET_ADDRESSES_BY_PERSON = "select CONCAT(Person.first_name, Person.last_name) as `Person`, Concat(street_address,city, state) as `Address`"
            + "from Person"
            + "inner join person_address pa on pa.person_id = Person.personId"
            + "inner join Address on Address.addressId = pa.address_id"
            + "where pa.person_id = ?;";
    private static final String SQL_FIND_ADDRESS_BY_LAST_NAME = "select CONCAT(Person.first_name, Person.last_name) as `Person`, Concat(street_address,city, state) as `Address`"
            + "from Address"
            + "inner join person_address pa on pa.address_id = Address.addressId"
            + "inner join Person on pa.person_id = Person.personId"
            + "where Address.addressId = ?";

    //get, list, update, delete
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Address createAddress(Address address) {
        jdbcTemplate.update(SQL_CREATE_ADDRESS,
                address.getStreetAddress(),
                address.getCity(),
                address.getState(),
                address.getZIPCode());

        Integer addressId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        address.setAddressId(addressId);

        return address;
    }

    public Address getAddress(Integer id) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_ADDRESS, new AddressDaoJdbcTemplateImpl.AddressMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Address> listAllAddresses() {
        return jdbcTemplate.query(SQL_LIST_ADDRESSES, new AddressMapper());
    }

    public Address updateAddress(Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getStreetAddress(),
                address.getCity(),
                address.getState(),
                address.getZIPCode(),
                address.getAddressId());

        return address;

    }

    public Address delete(Address address) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, address.getAddressId());
        return address;
    }

    public List<Address> getAddressesByPerson(Person p) {
        return jdbcTemplate.query(SQL_GET_ADDRESSES_BY_PERSON, new AddressMapper(), p.getPersonId());
    }

    public List<Address> findAddressesByLastName(String lastName) {
        return jdbcTemplate.query(SQL_FIND_ADDRESS_BY_LAST_NAME, new AddressMapper(), lastName);
    }

    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {
            Address a = new Address();
            a.setAddressId(rs.getInt("address_id"));
            a.setStreetAddress(rs.getString("street_address"));
            a.setCity(rs.getString("city"));
            a.setState(rs.getString("state"));
            a.setZIPCode(rs.getString("zipcode"));

            return a;
        }
    }

    /*
    private String addressId;    
    private String streetAddress;
    private String city;
    private String state;
    private String ZIPCode;

     */
}
