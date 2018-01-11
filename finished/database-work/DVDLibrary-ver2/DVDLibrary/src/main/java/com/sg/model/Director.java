/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.model;

import com.sg.bessieblog.commandmodel.RoleFormCommandModel;
import com.sg.bessieblog.dto.Role;
import com.sg.bessieblog.viewmodel.RolePageViewModel;
import java.util.List;

/**
 *
 * @author matt
 */
<<<<<<< HEAD:database-work/DVDLibrary-ver2/DVDLibrary/src/main/java/com/sg/model/Director.java
public class Director {
    
=======
public interface RoleService {
    public Role insertRole(Role role);
    public Role getRoleById(int roleId);
    public List<Role> getAllRoles();
    public void removeRole(Role role);
     public void removeRole(int roleId); 
    public void updateRole(RoleFormCommandModel rfcm);

    public RolePageViewModel getRolePageViewModel();
    public RoleFormCommandModel getRoleFormCommandModel(Role role);

    public Role getRoleByUserId(int id);
>>>>>>> f3db16cae3ac11dc43d808bce975167fc85a1faa:BessieBlog/src/main/java/com/sg/bessieblog/service/RoleService.java
}
