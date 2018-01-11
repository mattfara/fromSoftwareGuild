/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringProductDao;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.exception.NoProductException;
import java.util.List;

/**
 *
 * @author matt farabaugh
 */
public class FlooringProductServiceImpl implements FlooringProductService {

    FlooringProductDao productDao;

    public FlooringProductServiceImpl(FlooringProductDao productDao) {
        this.productDao = productDao;
    }
    

    @Override
    public Product getProductByType(String productType) 
            throws NoProductException, FlooringPersistenceException {
        return productDao.getProductByType(productType);
    }    
    
    @Override
    public Product addProduct(Product product) {
        productDao.addProduct(product);
        return product;
    }

    @Override
    public Product removeProduct(String productType) throws NoProductException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product editProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> getAllProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
