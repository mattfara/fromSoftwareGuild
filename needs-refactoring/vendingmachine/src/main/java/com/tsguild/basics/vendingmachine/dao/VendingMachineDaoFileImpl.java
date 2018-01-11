/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine.dao;

import com.tsguild.basics.vendingmachine.dto.Slot;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author matt
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    Map<Integer, Slot> slots = new HashMap();
    public static final String PRODUCTS_FILE = "products.txt";
    public static final String DELIMITER = "::";
    
//    @Override
//    public Map<Integer, Slot> returnFullInventory() throws VendingMachinePersistenceException{
//        loadInventory();
//        return slots;
//    }
    
    //why not have the product inventory be loaded in the constructor?    
    //am i asking the baby to walk before it is born? maybe need to do an "after" aop here
    //public VendingMachineDaoFileImpl() throws VendingMachinePersistenceException {loadInventory();}
    
    
    @Override //why this and fullInventory method above?
    public List<Slot> getAllProducts() throws VendingMachinePersistenceException {
            loadInventory();
            return new ArrayList<Slot>(slots.values());
    }
    
    @Override
    public Slot getProductBySlotNumber(int slotNum) throws ProductNotFoundException, VendingMachinePersistenceException {
        loadInventory();
//        if (slots.get(slotNum)==null){
//            throw new ProductNotFoundException("That product doesn't exist");
//        }
//        
        return slots.get(slotNum);
    }

    @Override //not sure what return type should be here
    public Slot addProduct(int slotNum, Slot slot) throws VendingMachinePersistenceException {
        loadInventory(); // do it just upon constructing?
        Slot newSlot = slots.put(slotNum, slot);
        writeInventory();
        return newSlot;
    }

    @Override //
    public Slot removeProduct(int slotNum) throws VendingMachinePersistenceException, ProductNotFoundException {
        loadInventory(); // do it just once upon constructing?
        if (getProductBySlotNumber(slotNum) != null){
            Slot removedSlot = slots.remove(slotNum);
            writeInventory();
            return removedSlot;
        } else {
            throw new ProductNotFoundException("There is no product in that slot.");
        }
    }
    
    @Override
    public Slot updateProduct(Slot updatedSlot) throws ProductNotFoundException,VendingMachinePersistenceException {
        
        slots.put(updatedSlot.getSlotNum(), updatedSlot);
         writeInventory(); // do it just once after run()? can call a method from here after it's done?
        return updatedSlot;
        
        
        
//        Slot updatedSlot = getProductBySlotNumber(slotNum);
//        updatedSlot.setProductName(productName);
//        slots.put(slotNum, updatedSlot);
//        writeInventory();
//        return updatedSlot;
    }
    
//    @Override //should be in service
//    public Slot buyProduct(Slot slot) throws VendingMachinePersistenceException {
//        loadInventory();
//        int currentStock = slot.getStock();
//        slot.setStock(currentStock-1);
//        slots.put(slot.getSlotNum(), slot); //need to create a new object every time?
//        writeInventory();
//        return slot;
//    }

    

    
    //combine all into one? would need a menu
    

//    @Override
//    public Slot updateProduct(int slotNum, BigDecimal price) throws ProductNotFoundException,  VendingMachinePersistenceException {
//        loadInventory();
//        Slot updatedSlot = getProductBySlotNumber(slotNum);
//        updatedSlot.setPrice(price);
//        slots.put(slotNum, updatedSlot);
//        writeInventory();
//        return updatedSlot;
//    }
//
//    @Override
//    public Slot updateProduct(int slotNum, int stock) throws ProductNotFoundException,  VendingMachinePersistenceException {
//        loadInventory();
//        Slot updatedSlot = getProductBySlotNumber(slotNum);
//        updatedSlot.setStock(stock);
//        slots.put(slotNum, updatedSlot);
//        writeInventory();
//        return updatedSlot;
//    }
    
//    @Override //should be in service
//    public BigDecimal getPrice(Slot slot) throws VendingMachinePersistenceException{
//        loadInventory();
//        return slot.getPrice();
//    }
    
//    @Override //should be in service
//    public boolean slotsStillOpen() throws VendingMachinePersistenceException{
//        loadInventory();
//        return slots.size()<7;
//    }
    
//    @Override //should be in service
//    public boolean isSlotTaken(int slotNum)throws VendingMachinePersistenceException{
//        loadInventory();
//        return slots.containsKey(slotNum);
//    }
    
//    @Override //should be in service
//    public boolean doesProductExist(int slotNum) throws ProductNotFoundException, VendingMachinePersistenceException{
//        loadInventory();
//        return getProductBySlotNumber(slotNum) != null;
//    }
    
    private void loadInventory() throws VendingMachinePersistenceException {
	Scanner scanner;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");    
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load data into memory.", e);
        }
        
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Slot currentSlot = new Slot(Integer.parseInt(currentTokens[0]));
            currentSlot.setProductName(currentTokens[1]);
            currentSlot.setStock(Integer.parseInt(currentTokens[2]));
            currentSlot.setPrice(new BigDecimal(currentTokens[3]));
            currentSlot.setParentCompany(currentTokens[4]);
            currentSlot.setVolumeInLiters(Double.parseDouble(currentTokens[5]));
            currentSlot.setCalories(Integer.parseInt(currentTokens[6]));
                    
                    
            slots.put(currentSlot.getSlotNum(), currentSlot);
        }
        scanner.close();
    }
    
    /**
	 * Writes all products in the list out to a PRODUCTS_FILE.  See loadInventory
	 * for file format.
	 * 
	 */
    private void writeInventory() throws VendingMachinePersistenceException {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PRODUCTS_FILE), true);
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save Inventory data.", e);
        }

        List<Slot> slotList = this.getAllProducts();
        for (Slot currentSlot : slotList) {
            out.println(currentSlot.getSlotNum() + DELIMITER
                    + currentSlot.getProductName() + DELIMITER 
                    + currentSlot.getStock()+ DELIMITER
                    + currentSlot.getPrice()+ DELIMITER
                    + currentSlot.getParentCompany() + DELIMITER
                    + currentSlot.getVolumeInLiters() + DELIMITER
                    + currentSlot.getCalories());
            
            out.flush();
        }
        out.close();
    }
}
