/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Power;
import com.sg.superherosightingsspringmvc.model.Sighting;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
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
public class SuperPersonDaoDbImpl implements SuperPersonDao {

    private JdbcTemplate jdbcTemplate; //declare object so we can use query, queryForObject, update methods later

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    } //constructor, we'll use this with beans later

    private static String SQL_INSERT_SUPERPERSON = "INSERT INTO SuperPerson (Name, Description, isGood) VALUES (?, ?, ?)";
    private static String SQL_GET_SUPERPERSON = "SELECT * FROM SuperPerson WHERE SuperPersonId = ?";
    private static String SQL_UPDATE_SUPERPERSON = "UPDATE SuperPerson SET Name = ?, Description = ?, isGood = ? WHERE SuperPersonId = ?";
    private static String SQL_DELETE_SUPERPERSON = "DELETE FROM SuperPerson WHERE SuperPersonId = ?";
    private static String SQL_LIST_SUPERPERSONS = "SELECT * FROM SuperPerson LIMIT ?,?";
    private static String SQL_COUNT_SUPERPERSONS = "SELECT COUNT(*) from SuperPerson;";

    private static String SQL_LIST_SUPERPERSONS_BY_SIGHTING = "SELECT `SuperPerson`.* FROM `SuperPerson` "
            + "inner join `SuperPerson_Sighting` "
            + "on `SuperPerson`.`SuperPersonId` = `SuperPerson_Sighting`.`SuperPersonId` "
            + "inner join `Sighting` "
            + "on `SuperPerson_Sighting`.`SightingId` = `Sighting`.`SightingId` "
            + "where `Sighting`.`SightingId` = ? ORDER BY `SuperPerson`.`Name` LIMIT ?,?;";
    private static String SQL_LIST_SUPERPERSONS_BY_SIGHTING_LOCATION = "SELECT `SuperPerson`.* FROM `SuperPerson` "
            + "inner join `SuperPerson_Sighting` on `SuperPerson`.`SuperPersonId` = `SuperPerson_Sighting`.`SuperPersonId` "
            + "inner join `Sighting` on `SuperPerson_Sighting`.`SightingId` = `Sighting`.`SightingId` "
            + "Inner join `Location` on `Sighting`.`LocationId` = `Location`.`LocationId` "
            + "where `Location`.`LocationId` = ? ORDER BY `SuperPerson`.`Name` LIMIT ?,?;";
    private static String SQL_LIST_SUPERPERSONS_BY_ORGANIZATION = "SELECT `SuperPerson`.* FROM `SuperPerson` "
            + "inner join `SuperPerson_Organization` "
            + "on `SuperPerson`.`SuperPersonId` = `SuperPerson_Organization`.`SuperPersonId` "
            + "inner join `Organization` "
            + "on `SuperPerson_Organization`.`OrganizationId` = `Organization`.`OrganizationId` "
            + "where `Organization`.`OrganizationId` = ? ORDER BY `SuperPerson`.`Name` LIMIT ?,?;";

    private static String SQL_LIST_SUPERPERSONS_BY_POWER = "SELECT `SuperPerson`.* FROM `SuperPerson`\n"
            + "inner join `SuperPerson_Power` on `SuperPerson`.`SuperPersonId` = `SuperPerson_Power`.`SuperPersonId`\n"
            + "inner join `Power` on `SuperPerson_Power`.`PowerId` = `Power`.`PowerId`\n"
            + "where `Power`.`PowerId` = ? ORDER BY `SuperPerson`.`Name` LIMIT ?,?;";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperPerson createSuperPerson(SuperPerson superPerson) {
        jdbcTemplate.update(SQL_INSERT_SUPERPERSON,
                superPerson.getName(), //THIS LIST MUST BE ORDERED BY PREPARED STATEMENT
                superPerson.getDescription(),
                superPerson.getIsGood());

        int superPersonId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        superPerson.setSuperPersonId(superPersonId);

        return superPerson;
    }

    @Override
    public SuperPerson getSuperPersonById(Integer superPersonId) {
        try {
            SuperPerson superPerson
                    = jdbcTemplate.queryForObject(SQL_GET_SUPERPERSON, new SuperPersonMapper(), superPersonId);
            return superPerson;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<SuperPerson> getAllSuperPersons(int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_SUPERPERSONS, new SuperPersonMapper(), offset, limit);
    }

    @Override
    public SuperPerson updateSuperPerson(SuperPerson superPerson) {
        jdbcTemplate.update(SQL_UPDATE_SUPERPERSON,
                superPerson.getName(),
                superPerson.getDescription(),
                superPerson.getIsGood(),
                superPerson.getSuperPersonId()
        );

        return superPerson;
    }

    @Override
    public SuperPerson deleteSuperPerson(SuperPerson superPerson) {
        jdbcTemplate.update(SQL_DELETE_SUPERPERSON, superPerson.getSuperPersonId());
        return superPerson;
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsByOrganization(Organization organization, int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_SUPERPERSONS_BY_ORGANIZATION,
                new SuperPersonMapper(), organization.getOrganizationId(), offset, limit);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsBySighting(Sighting sighting, int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_SUPERPERSONS_BY_SIGHTING, new SuperPersonMapper(), sighting.getSightingId(), offset, limit);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsBySightingLocation(Location location, int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_SUPERPERSONS_BY_SIGHTING_LOCATION,
                new SuperPersonMapper(), location.getLocationId(), offset, limit);
    }

    @Override
    public Integer countSuperPersons() {
        Integer startingSpCount = jdbcTemplate.queryForObject(SQL_COUNT_SUPERPERSONS, Integer.class);
        return startingSpCount;
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsByPower(Power power, int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_SUPERPERSONS_BY_POWER, 
                new SuperPersonMapper(), power.getPowerId(), offset, limit);
    }
    
    
    

    private static final class SuperPersonMapper implements RowMapper<SuperPerson> {

        @Override
        public SuperPerson mapRow(ResultSet rs, int i) throws SQLException {
            SuperPerson sp = new SuperPerson();
            sp.setSuperPersonId(rs.getInt("SuperPersonId"));
            sp.setName(rs.getString("Name"));
            sp.setDescription(rs.getString("Description"));
            sp.setIsGood(rs.getBoolean("isGood"));

            return sp;
        }
    }
}
