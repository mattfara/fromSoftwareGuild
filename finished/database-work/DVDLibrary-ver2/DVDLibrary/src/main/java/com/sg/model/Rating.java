/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.model;

import com.sg.bessieblog.commandmodel.UserFormCommandModel;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.viewmodel.UserMgmtViewModel;
import java.util.List;

/**
 *
 * @author matt
 */
<<<<<<< HEAD:database-work/DVDLibrary-ver2/DVDLibrary/src/main/java/com/sg/model/Rating.java
public class Rating {
    
=======
public interface UserService {
    public User insertUser(User user);
    public User getUserById(int userId);
    public List<User> getAllUsers();
    public List<User> getAllAdmins();
    public List<User> getAllContributors();
    public void removeUser(User user);
    public void updateUser(User user);
    public UserMgmtViewModel getUserMgmtViewModel();
    public UserFormCommandModel getUserFormCommandModel(User user);
    public User eagerlyGetUserById(int userId);
>>>>>>> f3db16cae3ac11dc43d808bce975167fc85a1faa:BessieBlog/src/main/java/com/sg/bessieblog/service/UserService.java
}
