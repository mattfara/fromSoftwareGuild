/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine.service;

import java.io.IOException;

/**
 *
 * @author matt
 */
public interface StatsPersistence {
    void loadStats() throws com.tsguild.basics.vendingmachine.service.VendingMachinePersistenceException;
    void writeStats() throws com.tsguild.basics.vendingmachine.service.VendingMachinePersistenceException;
}
