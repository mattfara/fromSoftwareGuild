/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.serverinventory.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import newpackage.dto.Server;

/**
 *
 * @author matt
 */
public class ServerDaoInMemImpl implements ServerDao {
    
    private Map<String, Server> serverMap = new HashMap<>(); 

    @Override
    public void addServer(Server server) {
        serverMap.put(server.getName(), server);
    }

    @Override
    public Server getServer(String name) {
        return serverMap.get(name);
    }

    @Override
    public void removeServer(String name) {
        serverMap.remove(name);
    }

    @Override
    public List<Server> getAllServers() {
        return new ArrayList<Server>(serverMap.values());
    }

    @Override
    public Map<String, List<Server>> getAllServersGroupByManufacturer() {
        //want all servers back but also sort into a map, where the name is the key and object is value
        //.values gets all the objects
        //.stream // no filter operation
        //terminal operation is collect
        //Collectors.groupingBy(Server::getManufacturer) -- group each server by manufacturer, get manufacturer from Server object getter
        //return it at the end -- it's giving you back a map consisting of sorted lists
        return serverMap.values()
                .stream()
                .collect(Collectors.groupingBy(Server::getManufacturer));
    }

    @Override
    public List<Server> getServersByManufacturer(String manufacturer) {
        //this one will use filtering, since we dont' want all of them back
        //we get the values, we stream it, and then filter based on a predicate
        return serverMap.values()
                .stream()
                .filter(s -> s.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    @Override
    public List<Server> getServersOlderThan(int ageInYears) {
        return serverMap.values()
                .stream()
                .filter(s -> s.getServerAge() > ageInYears)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Server>> getServersOlderThanGroupByManufacturer(int ageInYears) {
        return serverMap.values()
                .stream()
                .filter(s -> s.getServerAge() > ageInYears) //like a boolean built in
                .collect(Collectors.groupingBy(Server::getManufacturer));
    }   

    
    
    
    
}
