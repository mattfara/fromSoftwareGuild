/*
 * To change this license helocationer, choose License Helocationers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
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
public class LocationDaoDbImpl implements LocationDao {
    
    
    
    private JdbcTemplate jdbcTemplate; //declare object so we can use query, queryForObject, update methods later

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    } //constructor, we'll use this with beans later

    private static String SQL_INSERT_LOCATION = "INSERT INTO Location (Name, Description, Latitude, Longitude, AddressId) VALUES (?, ?, ?, ?, ?)";
    private static String SQL_GET_LOCATION = "SELECT * FROM Location WHERE LocationId = ?";
    private static String SQL_UPDATE_LOCATION = "UPDATE Location SET Name = ?, Description = ?, Latitude = ?, Longitude = ?, AddressId = ? WHERE LocationId = ?";
    private static String SQL_DELETE_LOCATION = "DELETE FROM Location WHERE LocationId = ?";
    private static String SQL_LIST_LOCATIONS = "SELECT * FROM Location LIMIT ?, ?";

    
    private static String SQL_LIST_LOCATIONS_BY_SUPERPERSON = "SELECT `Location`.* FROM `Location` "
            + "INNER JOIN `Sighting` "
            + "ON `Sighting`.`LocationId` = `Location`.`LocationId` "            
            + "INNER JOIN `SuperPerson_Sighting` "
            + "ON `SuperPerson_Sighting`.`SightingId` = `Sighting`.`SightingId` "
            + "INNER JOIN `SuperPerson` "
            + "ON `SuperPerson_Sighting`.`SuperpersonId` = `SuperPerson`.`SuperPersonId` "
            + "WHERE `SuperPerson`.`SuperPersonId` = ? ORDER BY `Location`.`Name` LIMIT ?,?;";
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location createLocation(Location location) {
        // Name, Description, Phone, IsGood, LocationId
        Address address = location.getAddress();
        
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getLatitude(),
                location.getLongitude(),
                address.getAddressId());
    //Name, Description, Latitude, Longitude, LocationId            
        
        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocationId(locationId);
        return location;
    }
    
    @Override
    public Location getLocationById(Integer locationId) {
        try {
            Location location = 
                    jdbcTemplate.queryForObject(SQL_GET_LOCATION, new LocationMapper(), locationId);
            return location;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Location> getAllLocations(int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_LOCATIONS, new LocationMapper(), offset, limit);
    }
    @Override
    public Location updateLocation(Location location) {
        Address address = location.getAddress();
        
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getLatitude(),
                location.getLongitude(),
                address.getAddressId(),
                location.getLocationId());
        return location;
        
        //Name, Description, Latitude, Longitude, LocationId
    }
    @Override
    public Location deleteLocation(Location location) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, location.getLocationId());
        return location;
    }

    @Override
    public List<Location> getAllLocationsBySuperPerson(SuperPerson superPerson, int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_LOCATIONS_BY_SUPERPERSON, new LocationMapper(), superPerson.getSuperPersonId(), offset, limit);
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("LocationId"));
            location.setName(rs.getString("Name"));
            location.setDescription(rs.getString("Description"));
            location.setLatitude(rs.getString("Latitude"));
            location.setLongitude(rs.getString("Longitude"));
            
            Address add = new Address();
            add.setAddressId(rs.getInt("AddressId"));
            location.setAddress(add);
            
            return location;
        }
    }
}
