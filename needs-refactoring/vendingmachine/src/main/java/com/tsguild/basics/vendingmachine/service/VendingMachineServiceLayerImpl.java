package com.tsguild.basics.vendingmachine.service;

import com.tsguild.basics.vendingmachine.dao.ProductNotFoundException;
import com.tsguild.basics.vendingmachine.dao.VendingMachineAuditDao;
import com.tsguild.basics.vendingmachine.dao.VendingMachineDao;
import static com.tsguild.basics.vendingmachine.dao.VendingMachineDaoFileImpl.DELIMITER;
import com.tsguild.basics.vendingmachine.dao.VendingMachinePersistenceException;
import com.tsguild.basics.vendingmachine.dto.Change;
import com.tsguild.basics.vendingmachine.dto.InventoryStats;
import com.tsguild.basics.vendingmachine.dto.Machine;
import com.tsguild.basics.vendingmachine.dto.Slot;
import com.tsguild.basics.vendingmachine.service.InsufficientFundsException;
import com.tsguild.basics.vendingmachine.service.ProductOutOfStockException;
import com.tsguild.basics.vendingmachine.service.VendingMachineServiceLayer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author matt
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer  {

    VendingMachineDao dao;
    VendingMachineAuditDao auditDao;
    
    //NEW
    Machine machine = new Machine();
    InventoryStats stats = new InventoryStats();
    //would need to save the machine-level and admin-decided values in a separate file

    
    //read only -- do i need these at all?
    @Override
    public Machine getMachine(){
        try{
        loadMachineValues();
        } catch (VendingMachinePersistenceException ex){}
        return machine;
    }
    
    @Override 
    public InventoryStats getStats(){
        return stats;
    }
    
    @Override
    public void setStats(InventoryStats newStats){
        this.stats=newStats;
    }
    //need the preceeding methods at all?
    
    //NEW -- trying to load stats into memory here
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao){
        this.dao=dao;
        this.auditDao=auditDao;
//        try{
//        loadMachineValues();
//        } catch(VendingMachinePersistenceException ex){
//            throw new VendingMachinePersistenceException("Unable to load machine stats");
//        }
//        updateInventoryStats(); //what if i called this from the controller?
    }

    @Override
    public void getUserMoney(BigDecimal userMoneyJustInserted, BigDecimal moneyAlreadyInMachine) throws VendingMachinePersistenceException{
        stats.setUserMoneyInserted(userMoneyJustInserted.add(moneyAlreadyInMachine));
        //might need to write values to 
    }
    
    
    @Override
    public Slot addProduct(int slotNum, Slot slot) throws VendingMachinePersistenceException {
        //auditDao.writeAuditEntry("Product added to machine");
        
        
        
        
        
        dao.addProduct(slotNum, slot);
        updateInventoryStats();
        return slot;
    }

    @Override
    public Slot getProductBySlotNumber(int slotNum) throws ProductNotFoundException, VendingMachinePersistenceException{
        return dao.getProductBySlotNumber(slotNum);
    }


    @Override
    public Slot updateProduct(Slot updatedSlot) throws ProductNotFoundException, VendingMachinePersistenceException{
        //auditDao.writeAuditEntry("Product name updated");
        
        Slot updatedProduct = dao.updateProduct(updatedSlot);
        updateInventoryStats(); 
        return updatedProduct;
        
    }

    @Override
    public List<Slot> getAllProducts() throws VendingMachinePersistenceException {
        return dao.getAllProducts();
    }

    
    @Override
    public Slot removeProduct(int slotNum) throws VendingMachinePersistenceException, ProductNotFoundException {
        //auditDao.writeAuditEntry("Product removed");
        Slot removedProduct = dao.removeProduct(slotNum);
        updateInventoryStats(); 
        return removedProduct;
    }


    @Override
    public Change returnChange(BigDecimal currentMoneyInserted) {
        
        Change coinCounts = new Change();
        
//        if (currentMoneyInserted.compareTo(new BigDecimal("0")) == 0){
//            coinCounts.setQuarters(0);
//            coinCounts.setDimes(0);
//            coinCounts.setNickels(0);
//            coinCounts.setPennies(0);
//        }
        
        BigDecimal[] coinValues = {new BigDecimal("25"), new BigDecimal("10"), new BigDecimal("5"), new BigDecimal("1")};
        int quarters = 0, dimes = 0, nickels = 0, pennies = 0;
        
        
        BigDecimal theChange = currentMoneyInserted;
        for (int currentCoin = 0; currentCoin<4; currentCoin++){
            BigDecimal coinValue = coinValues[currentCoin];
            while (theChange.compareTo(coinValue)>=0){
                theChange = theChange.subtract(coinValue);
                if (currentCoin==0){
                    quarters++;
                } else if (currentCoin==1){
                    dimes++;
                } else if (currentCoin==2){
                    nickels++;
                } else {
                    pennies++;
                }
            }
        }
        
        coinCounts.setQuarters(quarters);
        coinCounts.setDimes(dimes);
        coinCounts.setNickels(nickels);
        coinCounts.setPennies(pennies);
        
        stats.setUserMoneyInserted(new BigDecimal("0"));
        
        return coinCounts;
        
    }

    @Override
    public Change buyProduct(Slot currentProduct) throws InsufficientFundsException, ProductOutOfStockException, VendingMachinePersistenceException {
        loadMachineValues();
        
        seeWhetherFundsAreSufficient(currentProduct);
        seeWhetherProductInStock(currentProduct);
        //auditDao.writeAuditEntry(currentProduct.getProductName() + " sold");
        
        
        int currentStock = currentProduct.getStock();
        currentProduct.setStock(currentStock-1);
        dao.addProduct(currentProduct.getSlotNum(), currentProduct); //replaces old product
        
        //could subtract product price from the variable here, 
        
        BigDecimal productPrice = currentProduct.getPrice();
        //need to use setter now ***
        
        stats.setUserMoneyInserted(stats.getUserMoneyInserted().subtract(productPrice)) ;
        
        Change returnedChange = returnChange(stats.getUserMoneyInserted());
        stats.setUserMoneyInserted(new BigDecimal("0"));
        updateInventoryStats();
        return returnedChange;
            
    }
    
//    private BigDecimal getPrice(Slot slot)throws VendingMachinePersistenceException {
//        return dao.getPrice(slot); //want to move the functionality of the dao method into here
//    }
//    
    
//    private BigDecimal calculateChangeOwed(BigDecimal productPrice){
//        return currentMoneyInserted.subtract(productPrice);
//    }
    
    //business rules
    @Override
    public void seeWhetherFundsAreSufficient(Slot slot) throws InsufficientFundsException, VendingMachinePersistenceException {
        loadMachineValues();
        if (stats.getUserMoneyInserted().compareTo(slot.getPrice())<0){
            throw new InsufficientFundsException("ERROR: You need to insert more money");
        }
    }
    
    @Override
    public void doesProductExist(int slotNum) throws ProductNotFoundException, VendingMachinePersistenceException {
        if(dao.getProductBySlotNumber(slotNum)==null){
            throw new ProductNotFoundException("This product don't exist");
        }
    }
    
    @Override
    public void seeWhetherProductInStock(Slot slot) throws ProductOutOfStockException {
        if (slot.getStock() == 0){
            throw new ProductOutOfStockException("ERROR: That item is out of stock. Please choose another");
        }
    }
    
    //this would be implemented if the admin of vending machine became relevant to us
    private void validateProductData(Slot slot){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean slotsStillOpen()throws VendingMachinePersistenceException{        
        return dao.getAllProducts().size()<7;        
    }
    
    @Override
    public boolean isSlotTaken(int slotNum) throws VendingMachinePersistenceException, ProductNotFoundException{
        return dao.getProductBySlotNumber(slotNum)!=null;
    }

    @Override
    public void calculateMinCalories() throws VendingMachinePersistenceException {
        int minCalories = getAllProducts().stream()
                .mapToInt(slot -> slot.getCalories())
                .min()
                .getAsInt();
        
        stats.setMinCalories(minCalories);
    }

    @Override
    public void calculateMaxCalories() throws VendingMachinePersistenceException{
        int maxCalories = getAllProducts().stream()
                .mapToInt(slot -> slot.getCalories())
                .max()
                .getAsInt();
        
            stats.setMaxCalories(maxCalories);
    }

    @Override
    public void calculateAverageCalories() throws VendingMachinePersistenceException{
        //lambda way
        double avgCalories= getAllProducts().stream()
                .mapToInt(slot -> slot.getCalories())
                .average()
                .getAsDouble();
        
        stats.setCurrentAvgCalories(avgCalories);
    }
    
    @Override
    public void calculateMinVolume() throws VendingMachinePersistenceException{
        double minVolume = getAllProducts().stream()
                .mapToDouble(slot -> slot.getVolumeInLiters())
                .min()
                .getAsDouble();
        stats.setMinVolume(minVolume);
        
    }

    @Override
    public void calculateMaxVolume() throws VendingMachinePersistenceException{
        double maxVolume = getAllProducts().stream()
                .mapToDouble(slot -> slot.getVolumeInLiters())
                .max()
                .getAsDouble();
        
         stats.setMaxVolume(maxVolume);
    }

    @Override
    public void calculateAverageProductPrice() throws VendingMachinePersistenceException{
        List<Slot> allProducts = getAllProducts();
        BigDecimal total = new BigDecimal("0");
        int totalNumberOfProducts = allProducts.size();
        
        for (Slot currentProduct : allProducts ){
            total = total.add(currentProduct.getPrice());
        }
        
        BigDecimal avgPrice = total.divide(new BigDecimal(Integer.toString(totalNumberOfProducts)), RoundingMode.HALF_UP);
        stats.setCurrentAvgPrice(avgPrice);
        //could do this as lambda?
        //yes but kind of ugly and not readable
        
    }

    

    @Override
    public List<Slot> getProductsUnderAverageCalories() throws VendingMachinePersistenceException {
        double avgCalories = stats.getCurrentAvgCalories();
        
        return getAllProducts().stream()
                .filter(product -> product.getCalories() < avgCalories)
                .collect(Collectors.toList());
    }

    @Override
    public List<Slot> getProductsOverAverageCalories() throws VendingMachinePersistenceException {
        double avgCalories = stats.getCurrentAvgCalories();
        
        return getAllProducts().stream()
                .filter(product -> product.getCalories() > avgCalories)
                .collect(Collectors.toList());
    }
    
    
    @Override
    public boolean shouldBeRestocked(Slot slot) throws VendingMachinePersistenceException {
        try{
        loadMachineValues();
        } catch(VendingMachinePersistenceException ex){}
        return slot.getStock()<=machine.getLowStock();
    }

    @Override
    public List<Slot> getProductsUnderAveragePrice() throws VendingMachinePersistenceException{
        //fails because pulls from stats
        BigDecimal avgPrice = stats.getCurrentAvgPrice();
        
        return getAllProducts().stream()
                .filter(product -> product.getPrice().compareTo(avgPrice) == -1)
                .collect(Collectors.toList());
    }

    @Override
    public List<Slot> getProductsOverAveragePrice() throws VendingMachinePersistenceException{
        //fails because pulls from stats
        BigDecimal avgPrice = stats.getCurrentAvgPrice();
        
        return getAllProducts().stream()
                .filter(product -> product.getPrice().compareTo(avgPrice) == 1)
                .collect(Collectors.toList());
    }

    @Override
    public List<Slot> getDietFriendlyProducts() throws VendingMachinePersistenceException {
        loadMachineValues();
        return getAllProducts().stream()
                .filter(product -> product.getCalories() <= machine.getDietCalories())
                .collect(Collectors.toList());
    }

    @Override
    public void calculateMaxPrice() throws VendingMachinePersistenceException {
        BigDecimal maxPrice = new BigDecimal("0");
        List<Slot> allProducts = getAllProducts();
        for (Slot currentProduct : allProducts){
            BigDecimal currentProductPrice = currentProduct.getPrice();
            if (currentProductPrice.compareTo(maxPrice) == 1){
                maxPrice = currentProductPrice;
            }
        }
        stats.setMaxPrice(maxPrice);
    }

    @Override
    public void calculateMinPrice() throws VendingMachinePersistenceException {
        //this one fails test because it pulls from the stats object
        BigDecimal minPrice = stats.getMaxPrice();
        List<Slot> allProducts = getAllProducts();
        for (Slot currentProduct : allProducts){
            BigDecimal currentProductPrice = currentProduct.getPrice();
            if (currentProductPrice.compareTo(minPrice) == -1){
                minPrice = currentProductPrice;
            }
        }
        stats.setMinPrice(minPrice);
    }

    @Override
    public void calculateAverageVolumeInLiters () throws VendingMachinePersistenceException{
        //lambda way
        double avgLiters = getAllProducts().stream()
                .mapToDouble(slot -> slot.getVolumeInLiters())
                .average()
                .getAsDouble();
        
        stats.setCurrentAvgVolume(avgLiters);
        
    }
    
    
    
    @Override
    public List<Slot> getProductsUnderAverageSize() throws VendingMachinePersistenceException{
        double avgVolume = stats.getCurrentAvgVolume();
        
        return getAllProducts().stream()
                .filter(product -> product.getVolumeInLiters() < avgVolume)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Slot> getProductsOverAverageSize() throws VendingMachinePersistenceException{
        double avgVolume = stats.getCurrentAvgVolume();
        
        return getAllProducts().stream()
                .filter(product -> product.getVolumeInLiters() > avgVolume)
                .collect(Collectors.toList());
    }
    
    //this seems like a good place for aop -- run all these methods when a new product is added/updated/removed
    //but can't get it to 
    @Override
    public void updateInventoryStats() throws VendingMachinePersistenceException{
        calculateMinCalories();
        calculateMaxCalories();
        calculateAverageCalories();
        
        calculateMinVolume();
        calculateMaxVolume();
        calculateAverageVolumeInLiters();
        
        calculateMaxPrice();
        calculateMinPrice();
        calculateAverageProductPrice();
    }
    
    
    //read/write
    
    //for stats
    
    
    
    //for the machine
    private final String MACHINE_FILE = "machine.txt";
    private final String MACHINE_DELIMITER = "::";
    
    private void loadMachineValues() throws VendingMachinePersistenceException {
        Scanner scanner;
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(MACHINE_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load data into memory.", e);
        }
        
        String currentLine;
        String[] currentTokens;
        
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(MACHINE_DELIMITER);
            
            
            machine.setDietCalories(Integer.parseInt(currentTokens[0]));
            machine.setLowStock(Integer.parseInt(currentTokens[1]));
            machine.setMaxSlots(Integer.parseInt(currentTokens[2]));
            machine.setMaxStock(Integer.parseInt(currentTokens[3]));
            
            
        
        scanner.close();
    }
    
    
    //DON'T NEED THIS IF IT IS READ ONLY
//    private void writeMachineValues() throws VendingMachinePersistenceException {
//        PrintWriter out;
//
//        try {
//            out = new PrintWriter(new FileWriter(MACHINE_FILE), true);
//        } catch (IOException e) {
//            throw new VendingMachinePersistenceException("Could not save Inventory data.", e);
//        }
//
//        out.println(
//                machine.getDietCalories() + MACHINE_DELIMITER
//                + machine.getLowStock() + MACHINE_DELIMITER
//                + machine.getMaxSlots() + MACHINE_DELIMITER
//                + machine.getMaxStock()
//        );
//        out.flush();
//        out.close();
//    }
    
    //why not just keep this in memory? its a small number of calculations to do
    
//    private final String STATS_FILE = "inventoryStats.txt";
//    private final String STATS_DELIMITER = ";;";
//    
//    public void loadStats() throws VendingMachinePersistenceException {
//        Scanner scanner;
//        
//        try {
//            // Create Scanner for reading the file
//            scanner = new Scanner(
//                    new BufferedReader(
//                            new FileReader(STATS_FILE)));
//        } catch (FileNotFoundException e) {
//            throw new VendingMachinePersistenceException(
//                    "-_- Could not load data into memory.", e);
//        }
//        
//        String currentLine;
//        String[] currentTokens;
//        
//            currentLine = scanner.nextLine();
//            currentTokens = currentLine.split(STATS_DELIMITER);
//            InventoryStats stats = new InventoryStats();
//            
//            stats.setUserMoneyInserted(new BigDecimal(currentTokens[0]));
//            stats.setMinCalories(Integer.parseInt(currentTokens[1]));
//            stats.setMaxCalories(Integer.parseInt(currentTokens[2]));
//            stats.setCurrentAvgCalories(Double.parseDouble(currentTokens[3]));
//            stats.setMinVolume(Double.parseDouble(currentTokens[4]));
//            stats.setMaxVolume(Double.parseDouble(currentTokens[5]));
//            stats.setCurrentAvgVolume(Double.parseDouble(currentTokens[6]));
//            stats.setMinPrice(new BigDecimal(currentTokens[7]));
//            stats.setMaxPrice(new BigDecimal(currentTokens[8]));
//            stats.setCurrentAvgPrice(new BigDecimal(currentTokens[9]));
//            
//        
//        scanner.close();
//    }
//
//    public void writeStats() throws VendingMachinePersistenceException {
//        PrintWriter out;
//
//        try {
//            out = new PrintWriter(new FileWriter(STATS_FILE));
//        } catch (IOException e) {
//            throw new VendingMachinePersistenceException("Could not save Inventory data.", e);
//        }
//
//        
//
//        out.println(
//                  stats.getUserMoneyInserted() + STATS_DELIMITER
//                + stats.getMinCalories() + STATS_DELIMITER
//                + stats.getMaxCalories() + STATS_DELIMITER
//                + stats.getCurrentAvgCalories() + STATS_DELIMITER
//                + stats.getMinVolume() + STATS_DELIMITER
//                + stats.getMaxVolume() + STATS_DELIMITER
//                + stats.getCurrentAvgVolume() + STATS_DELIMITER
//                + stats.getMinPrice() + STATS_DELIMITER
//                + stats.getMaxPrice() + STATS_DELIMITER
//                + stats.getCurrentAvgPrice()
//        );
//        
//        out.flush();
//        out.close();
//    }
}
