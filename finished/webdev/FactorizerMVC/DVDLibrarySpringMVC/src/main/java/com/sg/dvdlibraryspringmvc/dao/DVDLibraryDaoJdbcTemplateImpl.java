/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.dto.DVD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matt
 */
public class DVDLibraryDaoJdbcTemplateImpl implements DVDLibraryDao {

    //DEPENDENCY INJECTION
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
       this.jdbcTemplate = jdbcTemplate;
    }
    
    //SQL STATEMENTS
    private static final String SQL_INSERT_DVD = "insert into dvd (`title`,`ReleaseDate`,`director`,`rating`,`notes`) values (?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE_DVD = "update dvd set title=?, releaseDate=?, director=?, rating=?, notes=? where dvdId=?;";
    private static final String SQL_DELETE_DVD = "delete from dvd where dvdId = ?";
    private static final String SQL_GET_ALL_DVDS = "select * from dvd";
    
    private static final String SQL_GET_DVD_BY_ID = "select * from dvd where dvdId = ?";
    private static final String SQL_GET_DVD_BY_TITLE = "select * from dvd where title = ?";
    private static final String SQL_GET_DVD_BY_RELEASE_DATE = "select * from dvd where releaseDate = ?";
    private static final String SQL_GET_DVD_BY_DIRECTOR = "select * from dvd where director = ?";
    private static final String SQL_GET_DVD_BY_RATING = "select * from dvd where rating = ?";
    
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly=false)
    public DVD addDVD(DVD dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD, dvd.getTitle(), dvd.getReleaseDate(), dvd.getDirector(), dvd.getRating(), dvd.getNotes());
        
        Integer dvdId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        dvd.setDvdId(dvdId);
        return dvd;
    }

    @Override
    public void updateDVD(DVD dvd) { 
        jdbcTemplate.update(SQL_UPDATE_DVD, 
            dvd.getTitle(), 
            dvd.getReleaseDate(), 
            dvd.getDirector(), 
            dvd.getRating(), 
            dvd.getNotes(), 
            dvd.getDvdId()
        );
    }

    @Override
    public void removeDVD(long dvdId) {
        jdbcTemplate.update(SQL_DELETE_DVD, dvdId);
    }

    @Override
    public List<DVD> getAllDVDs() {
        return jdbcTemplate.query(SQL_GET_ALL_DVDS, new DVDMapper());
    }

    @Override
    public DVD getDVDById(long dvdId) {
        return jdbcTemplate.queryForObject(SQL_GET_DVD_BY_ID, new DVDMapper(), dvdId);
    }

    @Override
    public List<DVD> searchDVDs(Map<SearchTerm, String> criteria) {
        String titleSearchCriteria =  
            criteria.get(SearchTerm.TITLE);
        String releaseYearSearchCriteria = 
             criteria.get(SearchTerm.RELEASE_YEAR);
        String directorSearchCriteria = 
            criteria.get(SearchTerm.DIRECTOR);
        String ratingSearchCriteria = 
            criteria.get(SearchTerm.RATING);
        
        if (titleSearchCriteria != null){
            return jdbcTemplate.query(SQL_GET_DVD_BY_TITLE, new DVDMapper(), titleSearchCriteria);
        }
        
        if (releaseYearSearchCriteria != null){
            return jdbcTemplate.query(SQL_GET_DVD_BY_RELEASE_DATE, new DVDMapper(), releaseYearSearchCriteria);
        }
        
        if (directorSearchCriteria != null){
            return jdbcTemplate.query(SQL_GET_DVD_BY_DIRECTOR, new DVDMapper(), directorSearchCriteria);
        }
        
        if (ratingSearchCriteria != null){
            return jdbcTemplate.query(SQL_GET_DVD_BY_RATING, new DVDMapper(), ratingSearchCriteria);
        }
        
        return null;
    }
    
        private static final class DVDMapper implements RowMapper<DVD> {
       @Override
       public DVD mapRow(ResultSet rs, int i) throws SQLException {
           DVD dvd = new DVD();
           dvd.setDirector(rs.getString("director"));
           dvd.setDvdId(rs.getInt("dvdId"));
           dvd.setNotes(rs.getString("notes"));
           dvd.setRating(rs.getString("rating"));
           dvd.setReleaseDate(rs.getInt("releaseDate"));
           dvd.setTitle(rs.getString("title"));
           
           return dvd;
       }
    }

}
