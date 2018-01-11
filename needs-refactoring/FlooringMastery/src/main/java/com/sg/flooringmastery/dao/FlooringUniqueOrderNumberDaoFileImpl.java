/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.dto.UniqueOrderNumber;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author matt farabaugh
 */
public class FlooringUniqueOrderNumberDaoFileImpl implements FlooringUniqueOrderNumberDao {

    @Override
    public UniqueOrderNumber getCurrentUniqueOrderNumber() {
        
        UniqueOrderNumber num = null;
        
        try {
            num = readUniqueOrderNumberFile();
        } catch (FlooringPersistenceException e) {
            // something
        }
        
        return num;
    }

    @Override
    public void setCurrentUniqueOrderNumber(UniqueOrderNumber num) {
        
        try {
            writeUniqueOrderNumberFile(num);
        } catch (FlooringPersistenceException e) {
            // something
        }
    }
    
    private UniqueOrderNumber readUniqueOrderNumberFile() throws FlooringPersistenceException {
        
        Scanner scanner;
        UniqueOrderNumber num = new UniqueOrderNumber();

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader("data/unique_order_number/UniqueOrderNumber.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException (
                "No unique order number was uniquely found.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            if (currentLine.length() == 0) {
                break;
            }

            num.setOrderNumber(Integer.parseInt(currentLine));

        }

        scanner.close();
        return num;
    }
    
    private void writeUniqueOrderNumberFile(UniqueOrderNumber num) throws FlooringPersistenceException {
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter("data/unique_order_number/UniqueOrderNumber.txt"));
        } catch (IOException e) {
            throw new FlooringPersistenceException(
                    "Could not save order number data to data file data.", e);
        }
        out.println(num.getOrderNumber());
        out.flush();
        out.close();        
    }    
    
}
