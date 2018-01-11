/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.dto.UniqueOrderNumber;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author matt farabaugh
 */
public class FlooringUniqueOrderNumberDaoInMemImpl implements FlooringUniqueOrderNumberDao {
    
    private UniqueOrderNumber orderNumber = new UniqueOrderNumber();
    
    public FlooringUniqueOrderNumberDaoInMemImpl() {
        
        try {
             this.orderNumber = readUniqueOrderNumberFile();
        } catch (FlooringPersistenceException e) {
            // do something?
        }
    }
    
    @Override
    public UniqueOrderNumber getCurrentUniqueOrderNumber() {
        return orderNumber;
    }

    @Override
    public void setCurrentUniqueOrderNumber(UniqueOrderNumber num) {
        orderNumber = num;
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
    
}
