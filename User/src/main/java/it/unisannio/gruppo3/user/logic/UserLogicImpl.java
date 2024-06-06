package it.unisannio.gruppo3.user.logic;

import it.unisannio.gruppo3.entities.User;
import it.unisannio.gruppo3.user.persistence.*;

import java.util.ArrayList;

public class UserLogicImpl implements UserLogic{

    UserDAO userDAO;

    public UserLogicImpl(){
        userDAO = new UserDAOMongo();
    }

    @Override
    public Long createUser(User user) {

        return userDAO.createUser(user);
    }

    @Override
    public User getUser(Long id) {
        return userDAO.getUser(id);
    }

    @Override
    public User updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userDAO.deleteUser(id);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public Long getNextId() {return userDAO.getNextId();}
}
