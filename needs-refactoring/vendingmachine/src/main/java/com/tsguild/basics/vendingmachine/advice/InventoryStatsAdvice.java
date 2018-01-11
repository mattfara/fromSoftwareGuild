///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.tsguild.basics.vendingmachine.advice;
//
//import com.tsguild.basics.vendingmachine.dao.VendingMachinePersistenceException;
//import com.tsguild.basics.vendingmachine.dto.InventoryStats;
//import com.tsguild.basics.vendingmachine.service.StatsPersistence;
//import com.tsguild.basics.vendingmachine.service.VendingMachineServiceLayer;
//
///**
// *
// * @author matt
// */
//public class InventoryStatsAdvice {
//    
//    VendingMachineServiceLayer service;
//    
//    public InventoryStatsAdvice(VendingMachineServiceLayer service) {
//        this.service = service;
//    }
//    
//    InventoryStats stats = service.getStats();
//    
//    //how do i write to the file without the separate file? I'd need to make the read/write methods public or add to impl
//    public void recalculateMinCalories() throws VendingMachinePersistenceException{
//        stats.setMinCalories(service.calculateMinCalories());
//    }
//    public void recalculateMaxCalories()throws VendingMachinePersistenceException{
//        stats.setMaxCalories(service.calculateMaxCalories());
//    }
//    public void recalculateAvgCalories()throws VendingMachinePersistenceException{
//        stats.setCurrentAvgCalories(service.calculateAverageCalories());
//    }
//    
//    public void recalculateMinVolume()throws VendingMachinePersistenceException{
//        stats.setMinVolume(service.calculateMinVolume());
//    }
//    public void recalculateMaxVolume()throws VendingMachinePersistenceException{
//        stats.setMaxVolume(service.calculateMaxVolume());
//    }
//    public void recalculateAvgVolume()throws VendingMachinePersistenceException{
//        stats.setCurrentAvgVolume(service.calculateAverageVolumeInLiters());
//        InventoryStatsAdvice
//    }
//    
//    public void recalculateMinPrice()throws VendingMachinePersistenceException{
//        stats.setMinPrice(service.calculateMinPrice());
//        
//    }
//    public void recalculateMaxPrice()throws VendingMachinePersistenceException{
//        stats.setMaxPrice(service.calculateMaxPrice());
//        
//    }
//    public void recalculateAvgPrice()throws VendingMachinePersistenceException{
//        stats.setCurrentAvgPrice(service.calculateAverageProductPrice());
//        
//    }
//    
//    
//}
