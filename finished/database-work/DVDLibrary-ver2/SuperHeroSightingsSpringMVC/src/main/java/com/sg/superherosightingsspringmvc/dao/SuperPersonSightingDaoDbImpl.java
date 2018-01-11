/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Sighting;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.model.SuperPersonSighting;
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
public class SuperPersonSightingDaoDbImpl implements SuperPersonSightingDao {

    private JdbcTemplate jdbcTemplate; //declare object so we can use query, queryForObject, update methods later

    private static String SQL_GET_SUPERPERSONSIGHTING_BY_SUPERPERSON_AND_SIGHTING = "SELECT * FROM SuperPerson_Sighting WHERE SuperPersonId = ? AND SightingId = ?";

    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    } //constructor, we'll use this with beans later

    private static String SQL_INSERT_SUPERPERSON_SIGHTING = "INSERT INTO SuperPerson_Sighting (SuperPersonId, SightingId) VALUES (?, ?)";
    private static String SQL_GET_SUPERPERSON_SIGHTING = "SELECT * FROM SuperPerson_Sighting WHERE SuperPerson_SightingId = ?";
    private static String SQL_UPDATE_SUPERPERSON_SIGHTING = "UPDATE SuperPerson_Sighting SET name = ? WHERE SuperPerson_SightingId = ?";
    private static String SQL_DELETE_SUPERPERSON_SIGHTING = "DELETE FROM SuperPerson_Sighting WHERE SuperPerson_SightingId = ?";
    private static String SQL_LIST_SUPERPERSON_SIGHTINGS = "SELECT * FROM SuperPerson_Sighting LIMIT ?, ?";
    
        @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperPersonSighting createSuperPersonSighting(SuperPersonSighting 
            superPersonSighting) {
        
        jdbcTemplate.update(SQL_INSERT_SUPERPERSON_SIGHTING,
                superPersonSighting.getSuperPerson().getSuperPersonId(),
                superPersonSighting.getSighting().getSightingId());
                
        
        int superPersonSightingId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        superPersonSighting.setSuperPersonSightingId(superPersonSightingId);
        return superPersonSighting;
    }
    @Override
    public SuperPersonSighting getSuperPersonSightingById(Integer superPersonSightingId) {
        try {
            SuperPersonSighting superPersonSighting = 
                    jdbcTemplate.queryForObject(SQL_GET_SUPERPERSON_SIGHTING, new SuperPersonSightingMapper(), superPersonSightingId);
            return superPersonSighting;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<SuperPersonSighting> getAllSuperPersonSightings(int offset, int limit) {
        List<SuperPersonSighting> allTheSightings = jdbcTemplate.query(SQL_LIST_SUPERPERSON_SIGHTINGS, new SuperPersonSightingMapper(), offset, limit);
        return allTheSightings;
    }

    
    
    @Override
    public SuperPersonSighting deleteSuperPersonSighting(SuperPersonSighting 
            superPersonSighting) {
        jdbcTemplate.update(SQL_DELETE_SUPERPERSON_SIGHTING, superPersonSighting.getSuperPersonSightingId());
        return superPersonSighting;
    }
    
    @Override
    public SuperPersonSighting getSuperPersonSightingBySuperPersonAndSighting(SuperPerson superPerson, Sighting sighting){
        Integer superPersonId = superPerson.getSuperPersonId();
        Integer sightingId = sighting.getSightingId();
        return jdbcTemplate.queryForObject(SQL_GET_SUPERPERSONSIGHTING_BY_SUPERPERSON_AND_SIGHTING, new SuperPersonSightingDaoDbImpl.SuperPersonSightingMapper(), superPersonId, sightingId);

    }

    
     private static final class SuperPersonSightingMapper implements RowMapper<SuperPersonSighting> {

        @Override
        public SuperPersonSighting mapRow(ResultSet rs, int i) throws SQLException {
            SuperPersonSighting spp = new SuperPersonSighting();
            
            SuperPerson sp = new SuperPerson();
            sp.setSuperPersonId(rs.getInt("SuperPersonId"));
            spp.setSuperPerson(sp);
            
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("SightingId"));
            spp.setSighting(sighting);
            
            spp.setSuperPersonSightingId(rs.getInt("SuperPerson_SightingId"));
            
            return spp;
        }
    }
}
