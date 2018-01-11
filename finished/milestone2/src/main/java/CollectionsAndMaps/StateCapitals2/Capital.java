/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollectionsAndMaps.StateCapitals2;

/**
 *
 * @author matt
 */
public class Capital {
    private String name;
    private int population;
    private int squareMileage;

    public String getName() {
        return name;
    }
//
//
    public int getPopulation() {
        return population;
    }
//
//    public void setPopulation(int population) {
//        this.population = population;
//    }
//
    public double getSquareMileage() {
        return squareMileage;
    }
//
//    public void setSquareMileage(double squareMileage) {
//        this.squareMileage = squareMileage;
//    }

    public Capital(String name, int population, int squareMileage){
        this.name = name;
        this.population = population;
        this.squareMileage = squareMileage;
    }
    
}
