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
public class PersonDaoJdbcTemplateImpl {

    private JdbcTemplate jdbcTemplate;

    @Inject //place beans proplery
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //SQL STATEMENTS
    private static final String SQL_CREATE_PERSON = "insert into Person (first_name, last_name, phone, email) values (?, ?, ?, ?)";
    private static final String SQL_DELETE_PERSON = "delete from Person where personId = ?";
    private static final String SQL_UPDATE_PERSON = "update Person set first_name = ?, last_name = ?, phone = ?, email = ? where personId = ?";
    private static final String SQL_GET_PERSON = "select * from Person where personId = ?";
    private static final String SQL_LIST_PEOPLE = "select * from Person";
    private static final String SQL_GET_PEOPLE_BY_ADDRESS = "select CONCAT(Person.first_name, Person.last_name) as `Person`, Concat(street_address,city, state) as `Address`"
            + "from Address"
            + "inner join person_address pa on pa.address_id = Address.addressId"
            + "inner join Person on pa.person_id = Person.personId"
            + "where Address.addressId = ?;";

    //get, list, update, delete
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Person createPerson(Person person) {
        jdbcTemplate.update(SQL_CREATE_PERSON,
                person.getFirstName(),
                person.getLastName(),
                person.getPhone(),
                person.getEmail());

        Integer personId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        person.setPersonId(personId);

        return person;
    }

    public Person getPerson(Integer id) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_PERSON, new PersonMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Person> listAllPeople() {
        return jdbcTemplate.query(SQL_LIST_PEOPLE, new PersonMapper());
    }

    public Person updatePerson(Person person) {
        jdbcTemplate.update(SQL_UPDATE_PERSON,
                person.getFirstName(),
                person.getLastName(),
                person.getPhone(),
                person.getEmail(),
                person.getPersonId());

        return person;

    }

    public Person delete(Person person) {
        jdbcTemplate.update(SQL_DELETE_PERSON, person.getPersonId());
        return person;
    }

    public List<Person> getPeopleByAddress(Address address) {
        return jdbcTemplate.query(SQL_GET_PEOPLE_BY_ADDRESS, new PersonMapper(), address.getAddressId());
    }

    private static final class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int i) throws SQLException {
            Person p = new Person();
            p.setFirstName(rs.getString("first_name"));
            p.setLastName(rs.getString("last_name"));
            p.setPhone(rs.getString("phone"));
            p.setEmail(rs.getString("email"));

            return p;
        }
    }

}

/*
private Integer personId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

*/
