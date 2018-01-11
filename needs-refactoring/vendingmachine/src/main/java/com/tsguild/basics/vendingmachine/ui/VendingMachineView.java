/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine.ui;

import static com.tsguild.basics.vendingmachine.dao.VendingMachineDaoFileImpl.DELIMITER;
import static com.tsguild.basics.vendingmachine.dao.VendingMachineDaoFileImpl.PRODUCTS_FILE;
import com.tsguild.basics.vendingmachine.dao.VendingMachinePersistenceException;
import com.tsguild.basics.vendingmachine.dto.Change;
import com.tsguild.basics.vendingmachine.dto.Slot;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author matt
 */
public class VendingMachineView {
    UserIO io;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");
    private static final String PW_FILE="password.txt";
    Random rand = new Random();
    
    public VendingMachineView(UserIO io){
        this.io=io;
    }
    
    public int printActionMenuAndGetSelection(){
        io.println("*********************************");
        io.println("******  >  Action Menu  <  ******");
        io.println("*********************************");
        io.println("1. Insert Change");
        io.println("2. View Products");
        io.println("3. Make a Selection");
        io.println("4. Click to highlight diet-friendly products");
        io.println("5. Press 'Return $' Button");
        io.println("6. See money you've inserted");
        io.println("7. Walk away from machine");
        io.println("8. Do Admin Stuff");
        io.println("");
        int action = io.readInt("Please select from the above choices: ",1,8);
        io.println("*********************************");
        io.println("*********************************");
        return action;
    }
    
    public void printProductMenu(List<Slot> fullInventory, boolean admin){
        io.println("*********************************");
        io.println("*****  >  Product Menu  <  ******");
        io.println("*********************************");
        showAllProducts(fullInventory, admin);        
        io.println("*********************************");
        io.println("*********************************");
    }
    
    public void waitForEnter(){
        io.readString("");
    }
    
    public int buyProduct(){
        //get the id of the product they want
        int slotChoice = io.readInt("Type the slot number of the product you want ");
        return slotChoice;            
    }
    
    public void displayGetYourDrinkMessage(){
        io.println("HHHMMMMMMM CLUNK CLUNK");
        io.println("Please take your drink and any change left over");
    }
    
    public int printAdminMenuAndGetSelection(){
        io.println("*********************************");
        io.println("******  >  Admin Menu  <  *******");
        io.println("*********************************");
        io.println("1. Add new product");
        io.println("2. Update info for one product");
        io.println("3. List all products");
        io.println("4. Remove a product");
        io.println("5. Show products that should be restocked");
        io.println("6. Show average product price");
        io.println("7. Show items priced under the average price");
        io.println("8. Show items priced over the average price");
        io.println("9. Show average product size");
        io.println("10. Show items smaller than the average size");
        io.println("11. Show items larger than the average size");
        io.println("12. Show average caloric value");
        io.println("13. Show items lower than average caloric value");
        io.println("14. Show items higher than average caloric value");
        io.println("15. Change admin password");
        io.println("16. Exit admin menu");
        io.println("");
        int action = io.readInt("Please select from the above choices: ",1,16);
        io.println("*********************************");
        io.println("*********************************");
        return action;
    }
    
    public boolean enterAndCheckPassword() throws VendingMachinePersistenceException{
        boolean passwordCorrect = false;
        String userPW="";
        int passwordAttempts = 0;
        while (!passwordCorrect){
            userPW = io.readString("Enter admin password: ");
            passwordAttempts++;
            if (userPW.equals(loadPassword())){
                return true;
            } else {
                io.println("Password incorrect. You have " + (3-passwordAttempts) + " tries left");
                if (passwordAttempts==4){
                    break;
                }
            }
        }
        io.println("Password attempted too many times. Go rob someone else's machine");
        return false;
    }
    
    public void changePassword() throws VendingMachinePersistenceException{
        int newPassword = io.readInt("Enter a new password (all numbers): ");
        String nextPassword = Integer.toString(newPassword);
        writePassword(nextPassword);
        io.print("Password successfully changed");
        displayPressEnterToContinue();
    }
    
    public BigDecimal getUserMoney(){
        String moneyIn = "";
        String[] dollarsAndCents=null;
        BigDecimal dollars = new BigDecimal("0");
        BigDecimal cents = new BigDecimal("0");
        BigDecimal dollarLimit = new BigDecimal("5");
        boolean moneyFormatCorrect = false;
        
        
        while(!moneyFormatCorrect){
            moneyIn = io.readString("Enter money as $x.xx : $");
            if (moneyIn.length()==4 && moneyIn.contains(".")){
                dollarsAndCents = moneyIn.split("\\.");
                if (userMoneyCorrectFormat(dollarsAndCents)){
                    moneyFormatCorrect=true;
                }
            } else {
                io.println("You didn't enter money properly. The machine accepted nothing. Try again");
            }
            
        }
            
        BigDecimal moneyInCents = convertUserMoneyToCents(dollarsAndCents);
        moneyInCents = verifyCoins(moneyInCents);
        
        io.println("The machine has accepted " + NumberFormat.getCurrencyInstance().format(moneyInCents.divide(new BigDecimal("100"))));
        displayPressEnterToContinue();
        return moneyInCents;        
    }
    
    public void displayPressEnterToContinue(){
        io.readString("Press ENTER to continue");
    }
    
    public BigDecimal verifyCoins(BigDecimal moneyInCents){
        boolean gotBadCoins = rand.nextInt(50)<5;
        if (gotBadCoins){
            io.println("Clink clink clink");
            io.println("The machine would not accept some coins");
            int amountToSpitBack = rand.nextInt(25+1);
            moneyInCents = moneyInCents.subtract(new BigDecimal(amountToSpitBack));
            return moneyInCents;
        } else {
            
            return moneyInCents;
        }
    }
    
    public void displaySuccessfullyAddedCoins(){
        io.println("The machine has accepted all your coins");
    }
    
    
    
    public boolean userMoneyCorrectFormat(String[] dollarsAndCents){
        return dollarsAndCents[0].length()==1 && dollarsAndCents[1].length()==2;
    }
    
    public void displayMoneyAlreadyInsertedMessage(){
        io.println("===Money Inserted===");
    }

    public void showInsertedMoney(BigDecimal moneyAlreadyInserted){
        io.println(NumberFormat.getCurrencyInstance().format(moneyAlreadyInserted.divide(new BigDecimal("100"))));
        displayPressEnterToContinue();
    }
    //******************************************************************
    //maybe belongs in the service layer
    public BigDecimal convertUserMoneyToCents(String[] dollarsAndCents){
        BigDecimal dollars; 
        BigDecimal cents;
        
        dollars = new BigDecimal(dollarsAndCents[0]);
        dollars = dollars.multiply(new BigDecimal("100"));
        
        cents = new BigDecimal(dollarsAndCents[1]);
        cents = cents.add(dollars);
            
        return cents;
    }
    
    /***********************************************/
    //limit is at 6 because that's how many products machine can contain
    public int makeSlotChoice (){
        int slotChoice = io.readInt("Enter the slot number: ",1,6);
        return slotChoice;
    }
    
    public void displayRemoveProductMessage(){
        io.println("===Remove Product===");
    }
    
    public boolean wantToKeepAddingItems(){
        int adminChoice = io.readInt("Do you want to keep adding items? 1 for yes, 2 for no ",1,2);
        return (adminChoice==1);
    }
    
    public void displayAddProductBanner(){
        io.println("");
        io.println("===Add New Product===");
        io.println("");
    }
    
    public void printSlotOccupiedMessage(){
        io.println("");
        io.println("This slot is occupied. Choose an empty slot.");
        io.println("");
    }
    
    public Slot enterNewProductInfo(int slotNum){
        Slot newSlot = new Slot(slotNum);
        
        String productName = io.readString("Enter product display name: ");
        int productStock = io.readInt("Stock this amount (up to 46): ",1,46);
        BigDecimal price = io.readBigDecimal("Enter a price in cents: ");
        String parentCompany = io.readString("Enter the parent company for this product: ");
        double volume = io.readDouble("Enter the volume per unit of new product ");
        int calories = io.readInt("Enter the number of calories per unit of this product: ");
        
        
        newSlot.setPrice(price);
        newSlot.setProductName(productName);
        newSlot.setStock(productStock);
        newSlot.setParentCompany(parentCompany);
        newSlot.setVolumeInLiters(volume);
        newSlot.setCalories(calories);
        
        
        
        return newSlot;
    }
    
    public void printInvalidChoiceMessage(){
        io.println("Choice invalid");
        io.println("");
    }
    
    public void displaySlotsAllOccupied(){
        io.println("");
        io.println("Slots all occupied");
        io.println("");
    }
    
        //need this here at all? maybe can just pass in the Slot created above right into the service/dao in controller
//    public Slot addProduct(Slot newSlot){
//        if (newSlot==null){
//            
//        } else{
//            
//        }
//        return someSlot;
//    }
    //will need this in the dao -- returning the full hashmap //
    //WHY NOT JUST USE A LIST?
    public void showAllProducts(List<Slot> allSlots, boolean admin){
        if (allSlots.size() > 0){
            for(Slot slot : allSlots){    
                io.println(slot.getSlotNum()+ " : " + slot.getProductName() + " ");
                io.println("      Price: " + NumberFormat.getCurrencyInstance().format(slot.getPrice().divide(new BigDecimal("100"))) + " Calories: " + slot.getCalories() + " Size: " + slot.getVolumeInLiters());
                
                if (admin){
                    io.println("      Stock: " + slot.getStock() + " Parent company: " + slot.getParentCompany());
                }
            }
            displayPressEnterToContinue();
        } else {
            io.println("No products to show");
            displayPressEnterToContinue();
        }
    }
    
    public void returnChange(Change returnedChange){
        io.println("Please collect your change.");
        io.println(returnedChange.getQuarters() + " quarters");
        io.println(returnedChange.getDimes() + " dimes");
        io.println(returnedChange.getNickels()+ " nickels");
        io.println(returnedChange.getPennies()+ " pennies");
        io.println("");
        displayReturnChangeMessage();
    }
    
    public void displayNoMoneyEnteredMessage(){
        io.println("You haven't given me money yet.");
    }
    
    public void displayReturnChangeMessage(){
        io.println("Change dispensed. Please check slot.");
        io.println("");
    }
    
    //display-only methods
    
    public void printArt(){
        io.println(
"|############################################|\n" +
"|#|                           |##############|\n" +
"|#|  =====  ..--''`  |~~``|   |##|````````|##|\n" +
"|#|  |   |  \\     |  :    |   |##| DON'T  |##|\n" +
"|#|  |___|   /___ |  | ___|   |##|   TIP  |##|\n" +
"|#|  /=__\\  ./.__\\   |/,__\\   |##| MACHINE|##|\n" +
"|#|  \\__//   \\__//    \\__//   |##|________|##|\n" +
"|#|===========================|##############|\n" +
"|#|```````````````````````````|##############|\n" +
"|#| =.._      +++     //////  |##############|\n" +
"|#| \\/  \\     | |     \\    \\  |#|`````````|##|\n" +
"|#|  \\___\\    |_|     /___ /  |#| _______ |##|\n" +
"|#|  / __\\\\  /|_|\\   // __\\   |#| |1|2|3| |##|\n" +
"|#|  \\__//-  \\|_//   -\\__//   |#| |4|5|6| |##|\n" +
"|#|===========================|#| |7|8|9| |##|\n" +
"|#|```````````````````````````|#| ``````` |##|\n" +
"|#| ..--    ______   .--._.   |#|[=======]|##|\n" +
"|#| \\   \\   |    |   |    |   |#|  _   _  |##|\n" +
"|#|  \\___\\  : ___:   | ___|   |#| ||| ( ) |##|\n" +
"|#|  / __\\  |/ __\\   // __\\   |#| |||  `  |##|\n" +
"|#|  \\__//   \\__//  /_\\__//   |#|  ~      |##|\n" +
"|#|===========================|#|_________|##|\n" +
"|#|```````````````````````````|##############|\n" +
"|############################################|\n" +
"|#|||||||||||||||||||||||||||||####```````###|\n" +
"|#||||||||||||PUSH|||||||||||||####\\|||||/###|\n" +
"|############################################|\n" +
"\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\///////////////////////\n" +
" |________________________________|CR98|___|");
    }
    
    public void displayOpeningPage(){
        printArt();
        io.println("Welcome to Snack-o-Matic");
        io.println(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
        io.println("");
    }

    public void displayItemSuccessfullyBoughtMessage() {
        io.readString("Please take your item below. ");
        displayPressEnterToContinue();
    }
    
    public void displayErrorMessage(String errorMsg) {
	    io.println("=== ERROR ===");
	    io.println(errorMsg);
    }
    
    public void displaySuccessfullyRemovedProductionMessage(){
        io.println("Product successfully removed");
        displayPressEnterToContinue();
    }
    
    public void displayUpdateProductMessage(){
        io.println("===Update Product Field===");
        io.println("");
    }
    
    public void displayProductSuccessfullyUpdatedMessage(){
        io.println("Product Info Successfully Updated");
        displayPressEnterToContinue();
    }
    
    public int updateProductInfoMenu(){
        int adminChoice;
        io.println("=====Product Update Menu=====");
        io.println("1. Update price -- in cents");
        io.println("2. Update stock -- maximum of 46");
        io.println("3. Update name");
        io.println("4. Update parent company");
        io.println("5. Update volume");
        io.println("6. Update calories");
        
        
        io.println("");
        
        adminChoice = io.readInt("Choose a field to update (1-6): ",1,6);
        return adminChoice;
    }
    
    public String getNewValueFromAdmin(){
        return io.readString("Enter new value: ");
    }
    
    public BigDecimal updateProductPrice(Slot slot){
        BigDecimal newPrice = io.readBigDecimal("Enter the new price now in pennies: ");
        return newPrice;
    }
    
    public int updateProductStock(Slot slot){
        int newStock = io.readInt("Enter the new stock now (up to 46): ",1,46);
        return newStock;
    }
    
    public String updateProductName(Slot slot){
        String newName = io.readString("Enter a new name: ");
        return newName;
                
    }
    
    public String updateParentCompany(Slot slot){
        String newParentCompany = io.readString("Please enter the parent company: ");
        return newParentCompany;
    }
    
    public double updateVolumeInLiters(Slot slot){
        double newVolumeInLiters = io.readDouble("Please enter the volume in liters: ");
        return newVolumeInLiters;
    }
    
    public int updateCalories(Slot slot){
        int newCalories = io.readInt("Please enter the calories: ");
        return newCalories;
    }
    
    private String loadPassword() throws VendingMachinePersistenceException {
        Scanner scanner;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");    
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PW_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load data into memory.", e);
        }
        
        String currentLine;
        currentLine = scanner.nextLine();    
        scanner.close();
        return currentLine;
    }
    
    private void writePassword(String newPW) throws VendingMachinePersistenceException {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PW_FILE), true);
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save Inventory data.", e);
        }

        out.println(newPW);
        out.flush();
        out.close();
    }

    public void printDrinkSuccessfullyAddedMessag() {
        io.println("Drink successfully added");
        displayPressEnterToContinue();
    }

    public void displayDietFriendlyProducts(List<Slot> dietFriendlyProducts, int dietCalories) {
        io.println("The following products contain fewer than " + dietCalories + " calories: ");
        dietFriendlyProducts.stream()
                .forEach(product -> {
                    io.println(product.getSlotNum()+" : " + product.getProductName());
                    io.println("        Calories per item: " + product.getCalories() + " Price: " + NumberFormat.getCurrencyInstance().format(product.getPrice().divide(new BigDecimal("100"))));
                });
        displayPressEnterToContinue();
    }
    //talk about this one
    public void displayPieceOfData(String messageType, Object data) {
        io.println(messageType + ": " + data);
        displayPressEnterToContinue();
    }
    
    
    
}
