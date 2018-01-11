/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.exception.NoProductException;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.exception.FlooringPersistenceException;
import java.util.List;

/**
 *
 * @author matt farabaugh
 */
public interface FlooringProductService {

    public Product addProduct(Product product);
    
    public Product removeProduct(String productType) throws NoProductException;
    
    public Product editProduct(Product product);
    
    public List<Product> getAllProducts();
    
    public Product getProductByType(String productType) 
            throws NoProductException, FlooringPersistenceException;
}