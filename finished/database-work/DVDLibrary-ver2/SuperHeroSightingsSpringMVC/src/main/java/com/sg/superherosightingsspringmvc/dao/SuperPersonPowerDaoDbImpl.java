/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Power;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.model.SuperPersonPower;
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
public class SuperPersonPowerDaoDbImpl implements SuperPersonPowerDao{

        private JdbcTemplate jdbcTemplate; //declare object so we can use query, queryForObject, update methods later

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    } //constructor, we'll use this with beans later

    private static String SQL_INSERT_SUPERPERSON_POWER = "INSERT INTO SuperPerson_Power (SuperPersonId, PowerId) VALUES (?, ?)";
    private static String SQL_GET_SUPERPERSON_POWER = "SELECT * FROM SuperPerson_Power WHERE SuperPerson_PowerId = ?";
    //private static String SQL_UPDATE_SUPERPERSON_POWER = "UPDATE SuperPerson_Power SET SuperPersonId = ?, PowerId = ? WHERE SuperPerson_PowerId = ?";
    private static String SQL_DELETE_SUPERPERSON_POWER = "DELETE FROM SuperPerson_Power WHERE SuperPerson_PowerId = ?";
    private static String SQL_LIST_SUPERPERSON_POWERS = "SELECT * FROM SuperPerson_Power LIMIT ?, ?";
    private static String SQL_GET_SUPERPERSONPOWER_BY_SUPERPERSON_AND_POWER = "SELECT * FROM SuperPerson_Power WHERE SuperPersonId = ? AND PowerId = ?";
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperPersonPower createSuperPersonPower(SuperPersonPower superPersonPower) {
        
        jdbcTemplate.update(SQL_INSERT_SUPERPERSON_POWER,
                superPersonPower.getSuperPerson().getSuperPersonId(),
                superPersonPower.getPower().getPowerId());
                
        
        int superPersonPowerId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        superPersonPower.setSuperPersonPowerId(superPersonPowerId);
        return superPersonPower;
    }
    @Override
    public SuperPersonPower getSuperPersonPowerById(Integer superPersonPowerId) {
        try {
            SuperPersonPower superPersonPower = 
                    jdbcTemplate.queryForObject(SQL_GET_SUPERPERSON_POWER, new SuperPersonPowerMapper(), superPersonPowerId);
            return superPersonPower;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<SuperPersonPower> getAllSuperPersonPowers(int offset, int limit) {
        List<SuperPersonPower> allThePowers = jdbcTemplate.query(SQL_LIST_SUPERPERSON_POWERS, new SuperPersonPowerMapper(), offset, limit);
        return allThePowers;
    }

    @Override
    public SuperPersonPower deleteSuperPersonPower(SuperPersonPower superPersonPower) {
        jdbcTemplate.update(SQL_DELETE_SUPERPERSON_POWER, superPersonPower.getSuperPersonPowerId());
        return superPersonPower;
    }
    
    @Override
    public SuperPersonPower getSuperPersonPowerBySuperPersonAndPower(SuperPerson superPerson, Power power){
        Integer superPersonId = superPerson.getSuperPersonId();
        Integer powerId = power.getPowerId();
        return jdbcTemplate.queryForObject(SQL_GET_SUPERPERSONPOWER_BY_SUPERPERSON_AND_POWER, new SuperPersonPowerMapper(), superPersonId, powerId);

    }
    
     private static final class SuperPersonPowerMapper implements RowMapper<SuperPersonPower> {

        @Override
        public SuperPersonPower mapRow(ResultSet rs, int i) throws SQLException {
            SuperPersonPower spp = new SuperPersonPower();
            
            SuperPerson sp = new SuperPerson();
            sp.setSuperPersonId(rs.getInt("SuperPersonId"));
            spp.setSuperPerson(sp);
            
            Power power = new Power();
            power.setPowerId(rs.getInt("PowerId"));
            spp.setPower(power);
            
            spp.setSuperPersonPowerId(rs.getInt("SuperPerson_PowerId"));
            
            return spp;
        }
    }
}

