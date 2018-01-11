/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.exception.NoProductException;
import com.sg.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author matt farabaugh
 */
public interface FlooringProductDao {
    
    public Product addProduct (Product product);
    
    public Product removeProduct (String productType);
    
    public Product editProduct (Product product);
    
    public Product getProductByType (String productType)
            throws NoProductException, FlooringPersistenceException;
    
    public List<Product> getAllProducts();
    
    
}
