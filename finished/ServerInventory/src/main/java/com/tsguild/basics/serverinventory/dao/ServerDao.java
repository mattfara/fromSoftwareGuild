/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.serverinventory.dao;

import java.util.List;
import java.util.Map;
import newpackage.dto.Server;

/**
 *
 * @author matt
 */


//dao is in charge of info -- we have the usual stuff here, but we add methods which provide small portions of the data, asking questions of the data
public interface ServerDao {
    public void addServer(Server server); //Crud
    public Server getServer(String name); //cRud
    public void removeServer(String name);//cruD
    public List<Server> getAllServers(); //cRud
    public Map<String, List<Server>> getAllServersGroupByManufacturer();//cRud -- filtering //for demonstration
    public List<Server> getServersByManufacturer(String manufacturer);//cRud
    public List<Server> getServersOlderThan(int ageInYears);//cRud
    public Map<String, List<Server>> getServersOlderThanGroupByManufacturer(int AgeInYears);//cRud also too complex for DAO -- sorting and filtering
    public double getAverageServerAge(); //Read kind of, but like with streams/lambdas // for demonstration
}
