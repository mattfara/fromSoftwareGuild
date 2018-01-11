/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine.controller;

import com.tsguild.basics.vendingmachine.dao.ProductNotFoundException;
import com.tsguild.basics.vendingmachine.dao.VendingMachinePersistenceException;
import com.tsguild.basics.vendingmachine.dto.Change;
import com.tsguild.basics.vendingmachine.dto.Slot;
import com.tsguild.basics.vendingmachine.service.InsufficientFundsException;
import com.tsguild.basics.vendingmachine.service.ProductOutOfStockException;
import com.tsguild.basics.vendingmachine.service.VendingMachineServiceLayer;
import com.tsguild.basics.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author matt
 */
public class VendingMachineController {
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    boolean keepUsing = true;
    
    
    //for wiring the app
    //want to load the machine file upon loading 
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view){
        this.service=service;
        this.view=view;
    }
    
    public void run() {
        
        view.displayOpeningPage();
        view.waitForEnter();
        //try{
        //service.updateInventoryStats(); THIS WORKS -- CHANGE BACK IF AOP FAILS -- TRY-CATCH TOO!!!
        //} catch (VendingMachinePersistenceException ex){}
        
        
        
        while (keepUsing){
            int actionChoice = view.printActionMenuAndGetSelection();
            useActionMenuChoice(actionChoice);
        }
        
        
    }
    
    public void useActionMenuChoice(int actionChoice) {
        
            
        
        switch(actionChoice){
            case 1: getUserMoney();
                    break;
            case 2: viewProductMenu(false);
                    break;
            case 3: buyProduct();
                    break;
            case 4: viewDietFriendlyMenu();
                    break;
            case 5: returnChange();
                    break;
            case 6: seeMoney(); 
                    break;
            case 7: keepUsing = false;
                    break;
            case 8: useAdminMenu();
                    break;
            default: view.printInvalidChoiceMessage();
        }
    }
    
    public void getUserMoney(){
        
        BigDecimal userMoneyJustInserted = view.getUserMoney();
        BigDecimal moneyAlreadyInMachine = service.getStats().getUserMoneyInserted();        
        
        //need a service method here that includes a write
        //1. add to interface
        //2. add to impl
        //3. change this method
        try{
        service.getUserMoney(userMoneyJustInserted, moneyAlreadyInMachine);
        } catch(VendingMachinePersistenceException ex){}
    }
    
    public void buyProduct() {
        int userSlotChoice = view.buyProduct();
        try {
            service.doesProductExist(userSlotChoice);
            Slot product = service.getProductBySlotNumber(userSlotChoice);
            Change yourChange = service.buyProduct(product);
            view.displayGetYourDrinkMessage();
            view.returnChange(yourChange);
            
        } catch(ProductNotFoundException | ProductOutOfStockException e){
            view.displayErrorMessage("Product does not exist or is out of stock");
        } catch(InsufficientFundsException e){
            view.displayErrorMessage("You have not entered enough money to buy this product");
        } catch(VendingMachinePersistenceException e){
            view.displayErrorMessage("Error accessing inventory file");
        }
    }
    
    public void viewProductMenu(boolean admin){
        try{
            List<Slot> allProducts = service.getAllProducts();
            view.showAllProducts(allProducts, admin);
        } catch(VendingMachinePersistenceException e){
            view.displayErrorMessage("Inventory file inaccessible");
        }
    }

//    public void purchaseProduct() {
//        boolean slotHasProduct = false;
//        Slot chosenSlot = null;
//        while (!slotHasProduct){
//            int slotChoice = view.buyProduct();
//            try {
//                chosenSlot = service.getProductBySlotNumber(slotChoice);
//                slotHasProduct = true;
//                Change changeOwed = service.buyProduct(chosenSlot);
//                view.displayItemSuccessfullyBoughtMessage();
//                view.returnChange(changeOwed);
//            } catch(ProductNotFoundException e){
//                view.displayErrorMessage("You can't purchase an item from an empty slot");
//            } catch(VendingMachinePersistenceException e){
//                view.displayErrorMessage("Inventory file inaccessible");
//                slotHasProduct=true;
//            } catch (InsufficientFundsException e){
//                view.displayErrorMessage("You don't have enough money for this purchase. Gimme more money");
//                slotHasProduct=true;
//            } catch (ProductOutOfStockException e){
//                view.displayErrorMessage("Out of stock");
//            }
//        }
//    }
    
    public void useAdminMenu(){
        boolean passwordEntered = false;
        
        try{
            passwordEntered = getAndCheckPassword();
        } catch(VendingMachinePersistenceException e){
            view.displayErrorMessage("Password file not reached");
        }
        
        boolean stayInAdminMenu = true;
        
//        try{
//        service.updateInventoryStats();
//        } catch (VendingMachinePersistenceException e){view.displayErrorMessage("Could not update inventory stats");}
        
        int adminChoice = 0;
        
        try{
            if (passwordEntered){
                while(stayInAdminMenu){
                    adminChoice = view.printAdminMenuAndGetSelection();
                    switch(adminChoice){
                        case 1: addProduct();
                                break;
                        case 2: updateProductInfo();
                                break;
                        case 3: viewProductMenu(true);
                                break;
                        case 4: removeProduct();
                                break;
                        case 5: showProductsToRestock();
                                break;
                        case 6: showAverageProductPrice();
                                break;
                        case 7: showProductsPricedUnderAverage();
                                break;
                        case 8: showProductsPricedOverAverage();
                                break;
                        case 9: showAverageProductVolume();
                                break;
                        case 10: showProductsSmallerThanAverage();
                                break;
                        case 11: showProductsLargerThanAverage();
                                break;
                        case 12: showAverageCaloricValue();
                                break;
                        case 13: showItemsLowerThanAverageCaloricValue();
                                break;
                        case 14: showItemsHigherThanAverageCaloricValue();
                                break;
                        case 15: view.changePassword();
                                break;
                        case 16: stayInAdminMenu = false;
                                break;
                        default: view.printInvalidChoiceMessage();
                                break;
                    }
                }
            }    
        } catch( VendingMachinePersistenceException e){
            view.displayErrorMessage("File could not be read");
        }                
    }
    
    
    public void updateProductInfo(){
        view.displayUpdateProductMessage();
        int slotChoice = view.makeSlotChoice();
        Slot productChosen = null;
        
        try{
            productChosen = service.getProductBySlotNumber(slotChoice);
            int adminChoice = view.updateProductInfoMenu();//mustslotChoice return int as a menu choice
            String newValue = view.getNewValueFromAdmin();
            
            
                switch(adminChoice){
                    case 1: productChosen.setPrice(new BigDecimal(newValue));
                            break;
                    case 2: productChosen.setStock(Integer.parseInt(newValue));
                            break;
                    case 3: productChosen.setProductName(newValue);
                            break;
                    case 4: productChosen.setParentCompany(newValue);
                            break;
                    case 5: productChosen.setVolumeInLiters(Double.parseDouble(newValue));
                            break;
                    case 6: productChosen.setCalories(Integer.parseInt(newValue));
                            break;
                            
                    default: view.displayErrorMessage("Invalid selection");
                            break;
                }
                view.displayUpdateProductMessage();
                service.updateProduct(productChosen);
            
        } catch(ProductNotFoundException e){
            view.displayErrorMessage("That is not a valid product to choose");
        } catch(VendingMachinePersistenceException e){
            view.displayErrorMessage("Inventory file inaccessible");
        } catch (Exception e){
            view.displayErrorMessage("The updated value is not valid");
        }
    }
    
    public void removeProduct(){
        view.displayRemoveProductMessage();
        boolean filledSlotChosen = false;
        
        while (!filledSlotChosen){
        
            int slotChoice = view.makeSlotChoice();
            
            try {
                service.removeProduct(slotChoice);
                view.displaySuccessfullyRemovedProductionMessage();
                filledSlotChosen=true;
            } catch (VendingMachinePersistenceException e) {
                view.displayErrorMessage("Log could not be written");
                filledSlotChosen=true;
            } catch (ProductNotFoundException e){
                view.displayErrorMessage(e.getMessage());
            }
        }
    }
    
    private void addProduct(){
        
            try{
                if (service.slotsStillOpen()){
                    view.displayAddProductBanner(); 
                    int slotChoice = view.makeSlotChoice();
                    if (!service.isSlotTaken(slotChoice)){
                        Slot newSlot = view.enterNewProductInfo(slotChoice);
                        service.addProduct(newSlot.getSlotNum(), newSlot);
                        view.printDrinkSuccessfullyAddedMessag();
                        //added straight from main menu
                    } else {
                        view.printSlotOccupiedMessage();
                    }
                } else {
                    view.displaySlotsAllOccupied(); 
                }
            } catch(VendingMachinePersistenceException e){
                view.displayErrorMessage("Inventory data could not be written/read");
            } catch(ProductNotFoundException ex){
                view.displayErrorMessage("No such product");
            }
        
    }
    
    public void returnChange(){
        //what if they haven't entered money yet?
        Change returnedChange = service.returnChange(service.getStats().getUserMoneyInserted());
        if (returnedChange == null){
            view.displayNoMoneyEnteredMessage();
        } else {
            view.returnChange(returnedChange);
        }
    }
    
    //split this into smaller methods
    
    
    public boolean getAndCheckPassword() throws VendingMachinePersistenceException{
        return view.enterAndCheckPassword();
    }

    public void seeMoney(){
        view.displayMoneyAlreadyInsertedMessage();
        BigDecimal moneyAlreadyInserted = service.getStats().getUserMoneyInserted();
        view.showInsertedMoney(moneyAlreadyInserted);
    }

    private void viewDietFriendlyMenu() {
        try {
            List<Slot> dietFriendlyProducts = service.getDietFriendlyProducts();
            int dietFriendlyCalories = service.getMachine().getDietCalories();
            view.displayDietFriendlyProducts(dietFriendlyProducts, dietFriendlyCalories); 
        } catch(VendingMachinePersistenceException ex) {
            view.displayErrorMessage("Could not load inventory file");
        }
        
    }

    private void showProductsToRestock() {
        List<Slot> lowStockSlots = new ArrayList();

        try {
            List<Slot> allProducts = service.getAllProducts();            
            for (Slot currentProduct : allProducts){
                if (service.shouldBeRestocked(currentProduct)){
                    lowStockSlots.add(currentProduct);
                }
            }
            } catch(VendingMachinePersistenceException ex) {}
            
            view.showAllProducts(lowStockSlots, true);
// way with lambda -- throw a persistenceException that I could get rid of here            
//            List<Slot> productsLowOnStock = allProducts.stream()
//                    .filter(product -> service.shouldBeRestocked(product))
//                    .collect(Collectors.toList());
//            

    }

    private void showAverageProductPrice() {
        //try{
        BigDecimal avgProductPrice = service.getStats().getCurrentAvgPrice();
        view.displayPieceOfData("The average price of products ",avgProductPrice); //could have one method to show a single piece of data, take in piece and string to show
        //} catch(VendingMachinePersistenceException ex) {
            //view.displayErrorMessage("Could not load inventory file");
        //}
    }

    private void showProductsPricedUnderAverage() {
        try {
        List<Slot> lowPriceProducts = service.getProductsUnderAveragePrice();
        view.showAllProducts(lowPriceProducts, true);
        } catch(VendingMachinePersistenceException ex) {
            view.displayErrorMessage("Could not load inventory file");
        }
    }

    private void showProductsPricedOverAverage() {
        try{
        List<Slot> highPriceProducts = service.getProductsOverAveragePrice();
        view.showAllProducts(highPriceProducts, true);
        } catch(VendingMachinePersistenceException ex) {
            view.displayErrorMessage("Could not load inventory file");
        }
    }

    private void showAverageProductVolume() {
        //try{
        double avgProductSize = service.getStats().getCurrentAvgVolume();
        view.displayPieceOfData("The average size of products ",avgProductSize);
        //} catch(VendingMachinePersistenceException ex) {
            //view.displayErrorMessage("Could not load inventory file");
        //}
    }

    private void showProductsSmallerThanAverage() {
        try{
        List<Slot> smallProducts = service.getProductsUnderAverageSize();
        view.showAllProducts(smallProducts, true);
        } catch(VendingMachinePersistenceException ex) {
            view.displayErrorMessage("Could not load inventory file");
        }
    }

    private void showProductsLargerThanAverage() {
        try{
        List<Slot> largeProducts = service.getProductsOverAverageSize();
        view.showAllProducts(largeProducts, true);
        } catch(VendingMachinePersistenceException ex) {
            view.displayErrorMessage("Could not load inventory file");
        }
    }

    private void showAverageCaloricValue() {
        //try{
        double avgProductCalories = service.getStats().getCurrentAvgCalories();
        view.displayPieceOfData("The average calories of products ",avgProductCalories);
        //} catch(VendingMachinePersistenceException ex) {
            //view.displayErrorMessage("Could not load inventory file");
        //}
    }

    private void showItemsLowerThanAverageCaloricValue() {
        try{
        List<Slot> lowCalorieProducts = service.getProductsUnderAverageCalories();
        view.showAllProducts(lowCalorieProducts, true);
        } catch(VendingMachinePersistenceException ex) {
            view.displayErrorMessage("Could not load inventory file");
        }
    }

    private void showItemsHigherThanAverageCaloricValue() {
        try{
        List<Slot> highCalorieProducts = service.getProductsOverAverageCalories();
        view.showAllProducts(highCalorieProducts, true);
        } catch(VendingMachinePersistenceException ex) {
            view.displayErrorMessage("Could not load inventory file");
        }
    }
    
    
    
}
