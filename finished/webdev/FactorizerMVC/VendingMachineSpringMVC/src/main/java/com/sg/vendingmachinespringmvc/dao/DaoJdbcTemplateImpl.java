/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Slot;
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
public class DaoJdbcTemplateImpl implements VendingMachineSpringMVCDao{

    JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    //SQL statements
    
    private static final String SQL_LIST_SLOTS = "select * from Slot;";
    private static final String SQL_UPDATE_SLOT = "update Slot set stock = ?, productName = ?, price = ?, parentCompany = ?, calories = ? where slotId = ?; ";
    private static final String SQL_GET_SLOT_BY_SLOTNUM = "select * from Slot where slotId = ?;";
    
    
    @Override //need this one
    public void updateSlot(Slot slot) {
        jdbcTemplate.update(SQL_UPDATE_SLOT, 
                slot.getStock(),
                slot.getProductName(),
                slot.getPrice(),
                slot.getParentCompany(),
                slot.getCalories(),
                slot.getSlotNum()
        );
        
    }

    @Override //and this one
    public List<Slot> getAllSlots() {
        return jdbcTemplate.query(SQL_LIST_SLOTS, new SlotMapper());
    }
    
    @Override
    public Slot getSlotBySlotNum(long slotNum) {
        return jdbcTemplate.queryForObject(SQL_GET_SLOT_BY_SLOTNUM, new SlotMapper(), slotNum);
    }

    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Slot addSlot(Slot slot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSlot(long slotNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public List<Slot> searchSlots(Map<SearchTerm, String> criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static final class SlotMapper implements RowMapper<Slot> {
       @Override
       public Slot mapRow(ResultSet rs, int i) throws SQLException {
           Slot slot = new Slot();
           slot.setCalories(rs.getInt("calories"));
           slot.setParentCompany(rs.getString("parentCompany"));
           slot.setPrice(rs.getBigDecimal("price"));
           slot.setProductName(rs.getString("productName"));
           slot.setSlotNum(rs.getInt("slotId"));
           slot.setStock(rs.getInt("stock"));
           
           return slot;
       }
    }
}
