/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author matt farabaugh
 */
public class FlooringView {
    UserIO io;
    
    public FlooringView(UserIO io){
        this.io=io;
    }
    public void displayDisplayOrdersBanner() {
        io.print("===Display Orders===");
    }
    
    public LocalDate[] askForDate(boolean editing) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //CHECK THIS IF PROBLEMATIC
        LocalDate dates[] = new LocalDate[2];
        LocalDate oldDate =io.readLocalDate("Enter the full date (yyyy-MM-dd): ");
        dates[0] = oldDate;
        if (editing){
            boolean isDateValid = false;
            while (!isDateValid){
            
                String newDate =io.readString("Enter the new date (Press ENTER to use same date): ");
                if (newDate.equals("")){
                    dates[1] = oldDate;
                    isDateValid=true;
                } else {
                    try{
                        LocalDate oldLocalDate = LocalDate.parse(newDate, formatter);
                        isDateValid=true;
                    } catch (DateTimeParseException e){
                        io.print("Please enter a valid date (yyyy-MM-dd)");
                    }
                }
            
            }
        }
        return dates;
    }
   
    public void displayAddAnOrderBanner() {
        io.print("===Add an Order===");
    }
    
    public void displayOpeningPage() {
        displayArt();
        io.print("Welcome to the Floor Doctor");
    }
    public void displayArt(){
    io.print(" ___________             ______       ______                                    ______       ______                  ______                \n" +
"|            |         .~      ~.   .~      ~.  |`````````,        |``````.   .~      ~.   .~      ~. `````|`````  .~      ~.  |`````````, \n" +
"|______      |        |          | |          | |'''|'''''         |       | |          | |                |      |          | |'''|'''''  \n" +
"|            |        |          | |          | |    `.            |       | |          | |                |      |          | |    `.     \n" +
"|            |_______  `.______.'   `.______.'  |      `.          |......'   `.______.'   `.______.'      |       `.______.'  |      `.   \n" +
"                                                                                                                                           ");
    }
    
    public void waitForEnter(){
        io.readString("Press ENTER to Continue");
    }
    public void printInvalidChoiceMessage() {
        io.print("Invalid choice");
    }
    
    public boolean askToCommit(String warningMessage) {
        String userAnswer = io.readString("Warning: " + warningMessage + " Are you sure you want to proceed? (y/n)");
        return userAnswer.equalsIgnoreCase("y") || userAnswer.equalsIgnoreCase("yes");
    }
        public int printActionMenuAndGetSelection(String mode){
        io.print("*********************************");
        io.print("******  >  Action Menu  <  ******");
        io.print("*********************************");
        io.print("1. Display All Orders For a Date");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Switch mode (currently in " + mode + ")");
        io.print("6. Quit");
        //io.print("7. Do Admin Stuff"); //this would switch to the admin controller
        io.print("");
        int action = io.readInt("Please select from the above choices: ",1,6);
        io.print("*********************************");
        io.print("*********************************");
        return action;
    }
   
    public void displayEditAnOrderBanner() {
        io.print("===Edit an Order===");
    }
    public Integer getOrderNumFromUser() {
        return io.readInt("Type order number: ");
    }
    //maybe combine to one method -- get info from user
    public Order setBasicOrderInfoForEdit(Order order, boolean editing) {
            
            String oldName = order.getCustomerName();
            String oldNameMessage = "("+oldName+")";
            BigDecimal oldFlooringArea = order.getFlooringArea();
            String oldFlooringAreaMessage = "("+oldFlooringArea+")";
            String oldState = order.getState();
            String oldStateMessage = "("+oldState+")";
            String oldProductType = order.getProductType();
            String oldProductTypeMessage = "("+oldProductType+")";

        
            
        String customerName = io.readString("Enter customer name " + (editing ? oldNameMessage : "") + ": ");
        if (customerName.equals("")) {customerName = order.getCustomerName();} 
        String productType = io.readString("Enter product type " +(editing ? oldProductTypeMessage : "")+ ": ");
        if (productType.equals("")) {productType = order.getProductType();}
        
        BigDecimal flooringAreaBD = null;
        boolean flooringAreaIsValid = false;
        while (!flooringAreaIsValid) {
            String flooringArea = io.readString("Enter flooring area (sq ft.) " +(editing ? oldFlooringAreaMessage : "")+ ": "); //not sure about this one....

            if (flooringArea.equals("")) {
                flooringAreaBD = order.getFlooringArea();
                flooringAreaIsValid = true;
            } else {
                try {
                    flooringAreaBD = new BigDecimal(flooringArea);
                    flooringAreaIsValid = true;
                } catch (NumberFormatException e) {
                    io.print("Please enter a valid number.");
                    waitForEnter();
                }
            }
        }
        
        String state = io.readString("Enter customer state of residence " +(editing ? oldStateMessage : "")+ ": ");        
        if (state.equals("")) {state = order.getState();}
        
        order.setCustomerName(customerName);
        order.setProductType(productType);
        order.setFlooringArea(flooringAreaBD);
        order.setState(state);
        
        return order;
        
    }
    
    public Order setBasicOrderInfoForAdd(Order order, boolean editing) {
            
        boolean inputIsValid = false;
        
        String customerName = null;
        while(!inputIsValid) {
            customerName = io.readString("Enter customer name : ");
            if (!customerName.equals("")) {
                inputIsValid = true;
            } else {
                displayExceptionMessage("Error: Cannot leave name blank.");
            }
        }
        
        inputIsValid = false;
        
        String productType = null;
        while(!inputIsValid) {
            productType = io.readString("Enter product type : ");
            if (!productType.equals("")) {
                inputIsValid = true;
            } else {
                displayExceptionMessage("Error: Cannot leave product type blank.");
            }
        }
        
        BigDecimal flooringAreaBD = null;
        boolean flooringAreaIsValid = false;
        while (!flooringAreaIsValid) {
            String flooringArea = io.readString("Enter flooring area (sq ft.) : "); //not sure about this one....

            if (flooringArea.equals("")) {
                flooringAreaBD = order.getFlooringArea();
                flooringAreaIsValid = true;
            } else {
                try {
                    flooringAreaBD = new BigDecimal(flooringArea);
                    if (flooringAreaBD.compareTo(new BigDecimal("0")) == 1) {  
                    flooringAreaIsValid = true;
                    } else {
                        displayExceptionMessage("Error: Flooring area must be greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    io.print("Please enter a valid number.");
                    waitForEnter();
                }
            }
        }
        
        inputIsValid = false;
        
        String state = null;
        while(!inputIsValid) {
            state = io.readString("Enter customer state of residence : ");
            if (!state.equals("")) {
                inputIsValid = true;
            } else {
                displayExceptionMessage("Error: Cannot leave state blank.");
            }
        }
        
        order.setCustomerName(customerName);
        order.setProductType(productType);
        order.setFlooringArea(flooringAreaBD);
        order.setState(state);
        
        return order;
        
    }
    
    public void displayRemoveAnOrderBanner() {
        io.print("===Remove an Order===");
        waitForEnter();
    }
    
    public String displayCurrencyFormat(BigDecimal currency) {
        return NumberFormat.getCurrencyInstance().format(currency);
    }
    
    //maybe make a boolean that would only show some of these if it is being used in a list
    public void showInfoForOneOrder(Order fullyEditedOrder, boolean inLargeList) {
        //maybe push all these into a hashmap
        
        String[] infoStrings = {
            "Info for order #"+fullyEditedOrder.getOrderNumber(), //
            "=============================", //
            "Customer name: " + fullyEditedOrder.getCustomerName(), //
            "Customer state: "+ fullyEditedOrder.getState(), //
            "Tax rate: " + fullyEditedOrder.getTaxRate() + "%", 
            "=============================",
            "Product type: " + fullyEditedOrder.getProductType(), //
            "Flooring area (SqFt): "+fullyEditedOrder.getFlooringArea(), //
            "=============================",
            "Cost per SqFt for materials: " + displayCurrencyFormat(fullyEditedOrder.getCostPerSqFt()),
            "Cost per SqFt for labor: " + displayCurrencyFormat(fullyEditedOrder.getLaborCostPerSqFt()),
            "Total cost for materials: " + displayCurrencyFormat(fullyEditedOrder.getMaterialCost()),
            "Total cost for labor: " + displayCurrencyFormat(fullyEditedOrder.getLaborCost()),
            "Total tax: " + displayCurrencyFormat(fullyEditedOrder.getTotalTax()),
            "=============================",
            "Grand total: " + displayCurrencyFormat(fullyEditedOrder.getTotalCost()) //
                        };        
        if (inLargeList){
                io.print(infoStrings[0]);
                io.print(infoStrings[1]);
                io.print(infoStrings[2]);
                io.print(infoStrings[3]);
                io.print(infoStrings[6]);
                io.print(infoStrings[7]);
                io.print(infoStrings[15]);
        } else {
            for(String info : infoStrings){
                io.print(info);
            }
        }
        
//        io.print("Info for order #"+fullyEditedOrder.getOrderNumber());
//        io.print("=============================");
//        io.print("Customer name: " + fullyEditedOrder.getCustomerName());
//        io.print("Customer state: "+ fullyEditedOrder.getState());
//        io.print("Tax rate: " + fullyEditedOrder.getTaxRate());
//        io.print("=============================");
//        io.print("Product type: " + fullyEditedOrder.getProductType());
//        io.print("Flooring area (SqFt): "+fullyEditedOrder.getFlooringArea());
//        io.print("=============================");
//        io.print("Cost per SqFt for materials: " + fullyEditedOrder.getCostPerSqFt()); //will probably format this for currency
//        io.print("Cost per SqFt for labor: " + fullyEditedOrder.getLaborCostPerSqFt());
//        io.print("Total cost for materials: " + fullyEditedOrder.getMaterialCost());
//        io.print("Total cost for labor: " + fullyEditedOrder.getLaborCost());
//        io.print("Total tax: " + fullyEditedOrder.getTotalTax());
//        io.print("=============================");
//        io.print("Grand total: " + fullyEditedOrder.getTotalCost());
        waitForEnter();
    }
    
    // format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
    public void showAllOrdersForDate(List<Order> listOfOrders, LocalDate date){
        boolean inLargeList = true;
        io.print("All orders for " + date + ": ");
        waitForEnter();
        for(Order currentOrder : listOfOrders){
            showInfoForOneOrder(currentOrder, inLargeList);
        }
        if (listOfOrders.size() == 0) {
            io.print("Error: no orders found for the date you entered!!!");
            waitForEnter();
        }     
    }
    
    public void displaySwitchApplicationModeBanner() {
        io.print("===Switch Application Mode===");
        waitForEnter();
    }
    public void displayYouAreInTrainingMode() {
        io.print("You are currently in TRAINING MODE");
    }
    public void displayYouAreInProductionMode() {
        io.print("You are currently in PRODUCTION MODE");
    }
    public void displayWarningAboutLosingCurrentWorkIfSwitchingMode() {
        io.print("Note that if you change modes, your unsaved work will be lost");
        waitForEnter();
    }
    public boolean askWantToSwitch() {
        String userAnswer = io.readString("Would you like to switch your mode? (y/n) ");
        return userAnswer.equalsIgnoreCase("y") || userAnswer.equalsIgnoreCase("yes");
    }
    public void displayOperationSuccessfulMessage(String successfulOperation) {
        io.print("Your " + successfulOperation + " operation was successful. Returning to main menu.");
        waitForEnter();
    }
    public void displayOperationAbandonedMessage(String abandonedOperation) {
        io.print("Your " + abandonedOperation + " operation has been abandoned. Returning to main menu.");
        waitForEnter();
    }
    public void displayExceptionMessage(String string) {
        io.print(string);
        waitForEnter();
    }
}
