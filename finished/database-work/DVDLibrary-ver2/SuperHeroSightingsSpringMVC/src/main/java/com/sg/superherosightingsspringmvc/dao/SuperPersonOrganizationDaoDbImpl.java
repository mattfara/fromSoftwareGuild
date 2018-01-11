/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.model.SuperPersonOrganization;
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
public class SuperPersonOrganizationDaoDbImpl implements SuperPersonOrganizationDao {

    private JdbcTemplate jdbcTemplate; //declare object so we can use query, queryForObject, update methods later

    private static String SQL_GET_SUPERPERSONORGANIZATION_BY_SUPERPERSON_AND_ORGANIZATION = "SELECT * FROM SuperPerson_Organization WHERE SuperPersonId = ? AND OrganizationId = ?";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    } //constructor, we'll use this with beans later

    private static String SQL_INSERT_SUPERPERSON_ORGANIZATION = "INSERT INTO SuperPerson_Organization (SuperPersonId, OrganizationId) VALUES (?, ?)";
    private static String SQL_GET_SUPERPERSON_ORGANIZATION = "SELECT * FROM SuperPerson_Organization WHERE SuperPerson_OrganizationId = ?";
    private static String SQL_DELETE_SUPERPERSON_ORGANIZATION = "DELETE FROM SuperPerson_Organization WHERE SuperPerson_OrganizationId = ?";
    private static String SQL_LIST_SUPERPERSON_ORGANIZATIONS = "SELECT * FROM SuperPerson_Organization LIMIT ?, ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperPersonOrganization createSuperPersonOrganization(SuperPersonOrganization superPersonOrganization) {

        jdbcTemplate.update(SQL_INSERT_SUPERPERSON_ORGANIZATION,
                superPersonOrganization.getSuperPerson().getSuperPersonId(),
                superPersonOrganization.getOrganization().getOrganizationId());

        int superPersonOrganizationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        superPersonOrganization.setSuperPersonOrganizationId(superPersonOrganizationId);
        return superPersonOrganization;
    }

    @Override
    public SuperPersonOrganization getSuperPersonOrganizationById(Integer superPersonOrganizationId) {
        try {
            SuperPersonOrganization superPersonOrganization
                    = jdbcTemplate.queryForObject(SQL_GET_SUPERPERSON_ORGANIZATION, new SuperPersonOrganizationMapper(), superPersonOrganizationId);
            return superPersonOrganization;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<SuperPersonOrganization> getAllSuperPersonOrganizations(int offset,
            int limit) {
        return jdbcTemplate.query(SQL_LIST_SUPERPERSON_ORGANIZATIONS, new SuperPersonOrganizationMapper(), offset, limit);
    }

    @Override
    public SuperPersonOrganization deleteSuperPersonOrganization(SuperPersonOrganization superPersonOrganization) {
        jdbcTemplate.update(SQL_DELETE_SUPERPERSON_ORGANIZATION, superPersonOrganization.getSuperPersonOrganizationId());
        return superPersonOrganization;
    }

    @Override
    public SuperPersonOrganization getSuperPersonOrganizationBySuperPersonAndOrganization(SuperPerson superPerson, Organization organization) {
        Integer superPersonId = superPerson.getSuperPersonId();
        Integer organizationId = organization.getOrganizationId();
        return jdbcTemplate.queryForObject(SQL_GET_SUPERPERSONORGANIZATION_BY_SUPERPERSON_AND_ORGANIZATION, new SuperPersonOrganizationMapper(), superPersonId, organizationId);

    }

    private static final class SuperPersonOrganizationMapper implements RowMapper<SuperPersonOrganization> {

        @Override
        public SuperPersonOrganization mapRow(ResultSet rs, int i) throws SQLException {
            SuperPersonOrganization spo = new SuperPersonOrganization();
            spo.setSuperPersonOrganizationId(rs.getInt("SuperPerson_OrganizationId"));

            // Lazy load superperson
            SuperPerson superPerson = new SuperPerson();
            superPerson.setSuperPersonId(rs.getInt("SuperPersonId"));
            spo.setSuperPerson(superPerson);

            // Lazy load organization
            Organization organization = new Organization();
            organization.setOrganizationId(rs.getInt("OrganizationId"));
            spo.setOrganization(organization);

            return spo;
        }
    }

}

//    @Override
//    public SuperPersonOrganization updateSuperPersonOrganization(SuperPersonOrganization superPersonOrganization) {
//        Location superPersonOrganizationLocation = superPersonOrganization.getLocation();
//        
//        jdbcTemplate.update(SQL_UPDATE_SUPERPERSON_ORGANIZATION,
//                java.sql.Date.valueOf(superPersonOrganization.getDate()),
//                superPersonOrganizationLocation.getLocationId(),
//                superPersonOrganization.getDescription(), 
//                superPersonOrganization.getSuperPersonOrganizationId()
//                );
//
//        return superPersonOrganization;
//    }
